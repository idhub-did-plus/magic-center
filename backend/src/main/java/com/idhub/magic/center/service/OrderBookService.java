package com.idhub.magic.center.service;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.OrderEntity;
import com.idhub.magic.center.entity.OrderState;
import com.idhub.magic.center.ustorage.IdentityStorage;
import com.idhub.magic.center.ustorage.MaterialWrapper;
import com.idhub.magic.common.event.MagicEventType;
import com.idhub.magic.common.ustorage.entity.Material;
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Service
public class OrderBookService implements OrderBook{
	@Autowired Datastore ds;
	@Autowired VerifiableCredentialService vcService;
	@Autowired MagicEventStore eventStore;
	@Override
	public List<Order> tome(String providerIdentity) {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).field("directTo").equal(providerIdentity).field("state").equal(OrderState.waiting.name()).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}
	Query<OrderEntity> check(String orderId, OrderState current, String provider){
		Query<OrderEntity> query = ds.createQuery(OrderEntity.class).field("id").equal(orderId).field("state").equal(current.name());
		if(provider == null)
			query = query.field("provider").doesNotExist();
		else
			query = query.field("provider").equal(provider);
		if(query.countAll() == 0) {
			throw new RuntimeException("invalide order state!");
			
		}
		return query;

	}
	 boolean stepForward(Query<OrderEntity> query, OrderState next, String provider){
		 UpdateOperations<OrderEntity> op = ds.createUpdateOperations(OrderEntity.class).set("state", OrderState.relayed.name()).set("receiveTime", new Date());;;
		 if(provider != null)
			 op = op.set("provider", provider);
		 UpdateResults n = ds.update(query, op);
		 if(n.getUpdatedCount() == 0)
			 return false;
		 return true;
	 }
	@Override
	public boolean receive(String identity, String orderId) {
		 Query<OrderEntity> query = check(orderId, OrderState.waiting,null);
		 return stepForward(query, OrderState.relayed, identity);
	}

	@Override
	public List<Order> listAll() {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

	@Override
	public void issueClaim(String providerIdentity,String orderId, String credential) {
		 Query<OrderEntity> query = check(orderId, OrderState.relayed,providerIdentity);
		
		 stepForward(query, OrderState.issued, null);
		 OrderEntity order = query.get();
		 vcService.store(order.getOrder().identity, orderId, credential);
		 eventStore.storeStringEvent(MagicEventType.claim_issued_event, order.getOrder().identity, credential);
	
	}

	@Override
	public IdentityData getIdentityInformation(String provider, String orderId) {
		Query<OrderEntity> query = check(orderId, OrderState.relayed,provider);
		OrderEntity order = query.get();
		IdentityStorage st = ds.find(IdentityStorage.class, "id", order.getOrder().identity).get();
		Query<MaterialWrapper> q = ds.find(MaterialWrapper.class, "material.identity", order.getOrder().identity);
		List<MaterialWrapper> data = q.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());
		return new IdentityData(order.getOrder().identity, st.getIdentityArchive(), mdata);
	}

	@Override
	public void refuseClaim(String identity, String orderId) {
		Query<OrderEntity> query = check(orderId, OrderState.relayed,identity);
		
		 stepForward(query, OrderState.refused, null);
		 OrderEntity order = query.get();
		// vcService.store(order.getOrder().identity, orderId, "refused");
		 eventStore.storeStringEvent(MagicEventType.claim_issued_event, order.getOrder().identity, "refused");

	}

}
