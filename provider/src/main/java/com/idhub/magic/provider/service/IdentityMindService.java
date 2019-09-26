package com.idhub.magic.provider.service;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.Material;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.kyc.idmind.ConsumerService;
import com.idhub.magic.provider.kyc.idmind.Converter;
import com.idhub.magic.provider.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

@Service
public class IdentityMindService {
	@Autowired
	Datastore ds;
	@Autowired
	IdentityMindProvider idm;
	@Autowired SimpleStorageService simpleStorageService;
	ObjectMapper mapper = new ObjectMapper();
	public Interaction evaluate(String orderId) {
		ProviderOrder order = ds.find(ProviderOrder.class, "id", orderId).get();
		Query<IdentityEntity> query = ds.find(IdentityEntity.class, "id", order.getOrder().identity);
		IdentityEntity ie = query.get();
		ConsumerService service = idm.getCustomerService();
		try {
			ConsumerKycRequest req = Converter.archve2request(ie.getData().getArchive());
			if(ie.getTransactionId() != null)
				req.tid = ie.getTransactionId();
			
			 ResponseBody body = service.customer(req, false).execute().body();
			 if(body == null)
				 throw new RuntimeException("request to idm api failed!");
			String json = new String(body.bytes());
			ConsumerKycResponse resp = mapper.readValue(json, ConsumerKycResponse.class);
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
	public void documentVerification(String orderId, String materialId, String description) throws IOException {
		ProviderOrder order = ds.find(ProviderOrder.class, "id", orderId).get();
		IdentityEntity iden = ds.find(IdentityEntity.class, "id", order.getOrder().identity).get();
		String tid = iden.getTransactionId();
		if(tid == null)
			throw new RuntimeException( "please call evaluate first!");
		Material mat = find(iden, materialId);
		byte[] data = this.simpleStorageService.get(materialId);
		MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", materialId + "." + mat.getExt(), RequestBody.create(MediaType.parse("image/*"), data));
		idm.getCustomerService().uploadDodumentVerification(tid, description,  filePart).execute().body();
	}
	Material find(IdentityEntity iden, String id) {
		for(Material m : iden.getData().getMaterials()) {
			if(m.getId().equals(id))
				return m;
			
		}
		return null;
	}
}
