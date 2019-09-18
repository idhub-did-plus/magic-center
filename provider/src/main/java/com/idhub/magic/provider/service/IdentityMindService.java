package com.idhub.magic.provider.service;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.kyc.idmind.ConsumerService;
import com.idhub.magic.provider.kyc.idmind.Converter;
import com.idhub.magic.provider.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;
import com.idhub.magic.provider.model.IdentityEntity;

@Service
public class IdentityMindService {
	@Autowired
	Datastore ds;
	@Autowired
	IdentityMindProvider idm;
	public Interaction evaluate(String orderId) {
		Order order = ds.find(Order.class, "id", orderId).get();
		Query<IdentityEntity> query = ds.find(IdentityEntity.class, "id", order.identity);
		IdentityEntity ie = query.get();
		ConsumerService service = idm.getCustomerService();
		try {
			ConsumerKycRequest req = Converter.archve2request(ie.getData().getArchive());
			if(ie.getTransactionId() != null)
				req.tid = ie.getTransactionId();
			ConsumerKycResponse resp = service.customer(req, false).execute().body();
			Interaction inteaction = new Interaction();
			inteaction.setOrderId(orderId);
			inteaction.setRequest(req);
			inteaction.setResponse(resp);
			ds.save(inteaction);
			if(ie.getTransactionId() == null) {
				String trasactionId = resp.mtid;
				UpdateOperations<IdentityEntity> up = ds.createUpdateOperations(IdentityEntity.class).set("transactionId", trasactionId);
				
				ds.update(query, up);
			}
			return inteaction;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
}
