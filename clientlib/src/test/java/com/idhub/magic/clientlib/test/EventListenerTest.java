package com.idhub.magic.clientlib.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.center.event.MagicEvent;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.FinancialProfile;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.IdentityInfo;
import com.idhub.magic.center.ustorage.entity.Material;
import com.idhub.magic.clientlib.ApiFactory;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.event.EventFetcher;
import com.idhub.magic.clientlib.http.RetrofitAccessor;
import com.idhub.magic.clientlib.interfaces.EventService;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

class EventListenerTest {
	ObjectMapper mapper = new ObjectMapper();
	String identity;
	EventService service;
	@BeforeEach
	void setUp() throws Exception {
		EventFetcher.getInstance();
		service = RetrofitAccessor.getInstance().getEventService();
		ApiFactory.getEventListenerService().listen(e->{
			try {
				
				Class type = Class.forName(e.eventClass);
				String encoded = e.event;
				byte[] json = Base64.getDecoder().decode(encoded);
				Object entity = mapper.readValue(json, type);
		
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
	}
	@Test
	void testDummyEvent() throws Exception
	{

			MagicResponse res = service.dummyEvent(identity).execute().body();

	}

	void testQuery() throws Exception
	{

			MagicResponse<List<MagicEvent>> es = service.queryEvents(identity).execute().body();
			System.out.println(es);

	}
}
