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

	@Override
	public boolean receive(String identity, String orderId) {
		 Query<OrderEntity> query = ds.createQuery(OrderEntity.class).field("id").equal(orderId).field("state").equal(OrderState.waiting.name());
		 UpdateOperations<OrderEntity> op = ds.createUpdateOperations(OrderEntity.class).set("provider", identity).set("state", OrderState.relayed.name()).set("receiveTime", new Date());;
		 UpdateResults n = ds.update(query, op);
		 if(n.getUpdatedCount() == 0)
			 return false;
		 return true;
	}

	@Override
	public List<Order> listAll() {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

	@Override
	public void issueClaim(String identity,String orderId, String credential) {
		
		 Query<OrderEntity> query = ds.createQuery(OrderEntity.class).field("id").equal(orderId).field("state").equal(OrderState.relayed.name());
		 UpdateOperations<OrderEntity> op = ds.createUpdateOperations(OrderEntity.class).set("state", OrderState.issued.name()).set("issueTime", new Date());;
		 UpdateResults n = ds.update(query, op);
		vcService.store(identity, orderId, credential);
		eventStore.store(MagicEventType.claim_issued_event, identity, credential);
	}

	@Override
	public IdentityData getIdentityInformation(String targetIdentity) {
		IdentityStorage st = ds.find(IdentityStorage.class, "id", targetIdentity).get();
		Query<MaterialWrapper> query = ds.find(MaterialWrapper.class, "material.identity", targetIdentity);
		List<MaterialWrapper> data = query.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());
		return new IdentityData(targetIdentity, st.getIdentityArchive(), mdata);
	}

	@Override
	public void refuseClaim(String identity, String orderId) {
		// TODO Auto-generated method stub
		
	}

}
