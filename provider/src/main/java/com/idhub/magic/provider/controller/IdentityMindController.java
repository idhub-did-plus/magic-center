package com.idhub.magic.provider.controller;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.kvc.entity.ClaimOrder;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.kyc.idmind.ConsumerService;
import com.idhub.magic.provider.kyc.idmind.Converter;
import com.idhub.magic.provider.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;
import com.idhub.magic.provider.model.IdentityEntity;


@RestController
@RequestMapping("/idm")
public class IdentityMindController {
	@Autowired
	IdentityMindProvider idm;
	@Autowired
	Datastore ds;

	@GetMapping("/evaluate")
	public MagicResponse evaluate(String orderId) {
		Order order = ds.find(Order.class, "id", orderId).get();
		IdentityEntity ie = ds.find(IdentityEntity.class, "id", order.identity).get();
		ConsumerService service = idm.getCustomerService();
		try {
			ConsumerKycRequest req = Converter.archve2request(ie.getData().getArchive());
			ConsumerKycResponse resp = service.customer(req, false).execute().body();
			Interaction inteaction = new Interaction();
			inteaction.setOrderId(orderId);
			inteaction.setRequest(req);
			inteaction.setResponse(resp);
			ds.save(inteaction);
			return new MagicResponse(inteaction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
