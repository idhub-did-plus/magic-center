package com.idhub.magic.provider.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.center.kyc.idmind.Converter;
import com.idhub.magic.center.kyc.idmind.ConsumerService;
import com.idhub.magic.center.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.center.kyc.idmind.entity.consumer.ConsumerKycResponse;

import okhttp3.ResponseBody;

public class CustomerTest {


	public void test() throws Exception {
		IdentityMindProvider provider = new IdentityMindProvider();
		provider.init();
		ConsumerService service = provider.getCustomerService();
		ConsumerKycResponse resp = service.customer(Converter.dummy(), false).execute().body();
	//	System.out.println(new String(resp.bytes()));
		
		
	}
	
	@Test
	public void testState() throws Exception {
		IdentityMindProvider provider = new IdentityMindProvider();
		provider.init();
		ConsumerService service = provider.getCustomerService();
		ConsumerKycResponse resp = service.retrievestate("9d647da0a5ec48d4a59aebcd50876289").execute().body();
		System.out.println(resp);
		
	}
	
	static public void main(String[] ss) throws Throwable {
		String json = "{\"user\":\"UNKNOWN\",\"upr\":\"UNKNOWN\",\"ednaScoreCard\":{\"sc\":[{\"test\":\"ed:1\",\"details\":\"true\"}],\"etr\":[{\"test\":\"ed:20\",\"details\":\"true\"},{\"test\":\"ed:31\",\"details\":\"true\"},{\"test\":\"ed:2\",\"details\":\"true\"},{\"test\":\"ed:1\",\"details\":\"true\"},{\"test\":\"ed:28\",\"details\":\"true\"}],\"er\":{\"profile\":\"DEFAULT\",\"reportedRule\":{\"name\":\"Sandbox Rule\",\"details\":\"[Fired] details\",\"description\":\"Rule fired from sandbox\",\"ruleId\":1000,\"testResults\":[{\"test\":\"ed:1\",\"fired\":true,\"details\":\"[Fired] details\",\"condition\":{\"left\":\"ed:1\",\"right\":true,\"operator\":\"eq\"},\"ts\":1567138039000,\"stage\":\"1\"}],\"resultCode\":\"ACCEPT\"}}},\"frn\":\"Sandbox Rule\",\"frp\":\"ACCEPT\",\"frd\":\"[Fired] details\",\"mtid\":\"9d647da0a5ec48d4a59aebcd50876289\",\"state\":\"A\",\"tid\":\"9d647da0a5ec48d4a59aebcd50876289\",\"erd\":\"Unknown User\",\"res\":\"ACCEPT\",\"rcd\":\"131,100,50005,120,1000,153,202\"}";
		ObjectMapper mapper = new ObjectMapper();
		ConsumerKycResponse resp = mapper.readValue(json, ConsumerKycResponse.class);
	}

}
