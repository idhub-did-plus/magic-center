package com.idhub.magic.provider.service;

import java.io.IOException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.agent.OrderBookFactory;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.ProviderOrderState;
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
		
			ProviderOrder po = new ProviderOrder(o);
			ds.save(po);
	
	}
	public List<ProviderOrder> list(ProviderOrderState state, int startPage, int pageSize){
		Query<ProviderOrder> query = ds.createQuery(ProviderOrder.class).field("state").equal(state.name()).offset(startPage * pageSize).limit(pageSize).order("createTime");
		return query.asList();
	}
	public IdentityEntity receive(String orderId) {
		
		 String providerIdentity = AccountManager.getMyAccount().getAddress();
		 fac.getOrderBook().receive(providerIdentity, orderId);
		 ProviderOrder order = ds.find(ProviderOrder.class, "id", orderId).get();
		
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
		return id;
		
	}

	public void drop(String orderId) {
		  Query<ProviderOrder> q = ds.find(ProviderOrder.class, "id", orderId);
		  ProviderOrder order = q.get();
		  if(!order.getState().equals(ProviderOrderState.unreceived.name()))
			  throw new RuntimeException("order cant be dropped after receiption!");
		  UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.dropped);
		  ds.update(q, operations);
		
	}

	public void refuseClaim(String orderId) {
		 Query<ProviderOrder> q = ds.find(ProviderOrder.class, "id", orderId);
		  ProviderOrder order = q.get();
		  if(!order.getState().equals(ProviderOrderState.processing.name()))
			  throw new RuntimeException("Invalid order state!");
		  String providerIdentity = AccountManager.getMyAccount().getAddress();
		  fac.getOrderBook().refuseClaim(providerIdentity, orderId);
		  UpdateOperations<ProviderOrder> operations = ds.createUpdateOperations(ProviderOrder.class).set("state", ProviderOrderState.refused);
		  ds.update(q, operations);
		
	}

	public void issueClaim(String orderId) {
				
	}
	
}
