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
import com.idhub.magic.common.parameter.MagicResponse;
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
	Query<ProviderOrder> check(String orderId, ProviderOrderState current) {
		Query<ProviderOrder> query = ds.createQuery(ProviderOrder.class).field("id").equal(orderId).field("state").equal(current.name());
		if(query.countAll() == 0) {
			throw new RuntimeException("invalid order state!");
		}
		return query;
		

	}
	void stepForward(Query<ProviderOrder> query,ProviderOrderState next) {
		
		
		UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", next.name());
		ds.update(query, operations);

	}
	public IdentityEntity receive(String orderId) {
		Query<ProviderOrder> query = check(orderId, ProviderOrderState.unreceived);
		try {
		 String providerIdentity = AccountManager.getMyAccount().getAddress();
		 fac.getOrderBook().receive(providerIdentity, orderId).execute().body();
	
		
		 MagicResponse<IdentityData> info = fac.getOrderBook().getIdentityInformation(providerIdentity, orderId).execute().body();
		if(!info.isSuccess())
			throw new RuntimeException( "from orderbook:" + info.getMessage());
		IdentityEntity id = new IdentityEntity(info.getData());
		ds.save(id);
		stepForward(query, ProviderOrderState.processing);
		
		return id;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void drop(String orderId) {
		Query<ProviderOrder> query = check(orderId, ProviderOrderState.unreceived);
		 
		  ProviderOrder order = query.get();
		  
		  stepForward(query,ProviderOrderState.dropped );
		
	}

	public void refuseClaim(String orderId) {
		Query<ProviderOrder> query = check(orderId, ProviderOrderState.processing);
		
		  String providerIdentity = AccountManager.getMyAccount().getAddress();
		  try {
			MagicResponse resp = fac.getOrderBook().refuseClaim(providerIdentity, orderId).execute().body();
			if(!resp.isSuccess())
				throw new RuntimeException( "from orderbook:" + resp.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		  stepForward(query,ProviderOrderState.refused );
		 
		
	}

	public VerifiableClaimEntity issueClaim(String orderId) {
		Query<ProviderOrder> query = check(orderId, ProviderOrderState.processing);
		
		ProviderOrder order = query.get();
		
		String identity = order.getOrder().identity;
		String subject = "did:" + "erc1056:" + identity;
		
		IdentityEntity data = ds.find(IdentityEntity.class, "id", identity).get();
		String claimType = order.getOrder().claimType;
		try {
			 VerifiableClaimEntity claim = ClaimUtils.issueClaim(subject, claimType, data.getData().getArchive().getIdentityInfo().getCountry(), "unknown");
			claim.setId(orderId);
			 
			 MagicResponse resp = fac.getOrderBook().issueClaim(AccountManager.getMyAccount().getAddress(), orderId, claim.getJsonld()).execute().body();
			 if(!resp.isSuccess())
					throw new RuntimeException( "from orderbook:" + resp.getMessage());
			 ds.save(claim);
			 stepForward(query,ProviderOrderState.issued );
			 
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
