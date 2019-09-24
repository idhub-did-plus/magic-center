package com.idhub.magic.provider.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.common.claim.VerifiableClaim;
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.agent.OrderBookFactory;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.ProviderOrderState;
import com.idhub.magic.provider.model.VerifiableClaimEntity;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Service
public class OrderRepository {
	@Autowired Datastore ds;
	@Autowired
	OrderBookFactory fac;
	public void store(List<Order> my) {
		for(Order o : my) {
			
			store(o);
		}
	}
		
	public void store(Order o) {
		
			
			if(ds.find(ProviderOrder.class, "id", o.id).asList().size() == 0) {
				ProviderOrder po = new ProviderOrder(o);
				ds.save(po);
			}
	
	}
	public List<ProviderOrder> list(ProviderOrderState state, int startPage, int pageSize){
		Query<ProviderOrder> query = ds.createQuery(ProviderOrder.class).field("state").equal(state.name()).offset(startPage * pageSize).limit(pageSize).order("createTime");
		return query.asList();
	}
	public IdentityEntity receive(String orderId) {
		
		 String providerIdentity = AccountManager.getMyAccount().getAddress();
		 fac.getOrderBook().receive(providerIdentity, orderId);
		 Query<ProviderOrder> query = ds.find(ProviderOrder.class, "id", orderId);
		 
		 ProviderOrder order =query.get();
		
		 IdentityData info;
		try {
			info = fac.getOrderBook().getIdentityInformation(providerIdentity, order.getOrder().identity).execute().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		IdentityEntity id = new IdentityEntity(info);
		ds.save(id);
		UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.processing.name());
		  ds.update(query, operations);
		return id;
		
	}

	public void drop(String orderId) {
		  Query<ProviderOrder> q = ds.find(ProviderOrder.class, "id", orderId);
		  ProviderOrder order = q.get();
		  if(!order.getState().equals(ProviderOrderState.unreceived.name()))
			  throw new RuntimeException("order cant be dropped after receiption!");
		  UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.dropped.name());
		  ds.update(q, operations);
		
	}

	public void refuseClaim(String orderId) {
		 Query<ProviderOrder> q = ds.find(ProviderOrder.class, "id", orderId);
		  ProviderOrder order = q.get();
		  if(!order.getState().equals(ProviderOrderState.processing.name()))
			  throw new RuntimeException("Invalid order state!");
		  String providerIdentity = AccountManager.getMyAccount().getAddress();
		  fac.getOrderBook().refuseClaim(providerIdentity, orderId);
		  UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.refused.name());
		  ds.update(q, operations);
		
	}

	public VerifiableClaimEntity issueClaim(String orderId) {
		Query<ProviderOrder> q = ds.find(ProviderOrder.class, "id", orderId);
		ProviderOrder order = q.get();
		
		String identity = order.getOrder().identity;
		String subject = "did:" + "erc1056:" + identity;
		
		IdentityEntity data = ds.find(IdentityEntity.class, "id", identity).get();
		String claimType = order.getOrder().claimType;
		try {
			 VerifiableClaimEntity claim = ClaimUtils.issueClaim(subject, claimType, data.getData().getArchive().getIdentityInfo().getCountry(), "unknown");
			claim.setId(orderId);
			 ds.save(claim);
			 fac.getOrderBook().issueClaim(AccountManager.getMyAccount().getAddress(), orderId, claim.getJsonld()).execute().body();
			 UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.issued.name());
			  ds.update(q, operations);
			return claim;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
			
	}

	public int size(ProviderOrderState state) {
		Query<ProviderOrder> query = ds.createQuery(ProviderOrder.class).field("state").equal(state.name());
		return (int)query.countAll();
	}
	
}
