package com.idhub.magic.clientlib.http;

import java.io.IOException;

import org.web3j.crypto.Credentials;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.DelegationService;
import com.idhub.magic.clientlib.interfaces.EventService;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;
import com.idhub.magic.clientlib.interfaces.KycService;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitAccessor {
	static RetrofitAccessor instance = new RetrofitAccessor();
	static public RetrofitAccessor getInstance() {
		return instance;
	}
	 RetrofitAccessor() {
		 
		super();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		init();
		// TODO Auto-generated constructor stub
	}
	IdentityStorage identityStorage;
	EventService eventService;
	DelegationService delegationService;
	KycService kycService;
	ObjectMapper mapper = new ObjectMapper();
 
	
	 public IdentityStorage getIdentityStorage() {
		return identityStorage;
	}

	void init() {
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				HttpUrl url = request.url();
				String identity = url.queryParameter("identity");
				long tp = System.currentTimeMillis();
				long ts = System.currentTimeMillis();
				String sgt = authenticate(identity, ts);
				String nurl = url.url().toString() + "&timestamp=" + ts;

				Request.Builder builder = request.newBuilder().addHeader("signature", sgt).url(nurl);
				return chain.proceed(builder.build());
			}
		}).build();
		
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080")
				.addConverterFactory(JacksonConverterFactory.create(mapper)).client(client).build();

		identityStorage = retrofit.create(IdentityStorage.class);
		eventService = retrofit.create(EventService.class);
		delegationService = retrofit.create(DelegationService.class);
		kycService =  retrofit.create(KycService.class);
	}

	public KycService getKycService() {
		return kycService;
	}
	public DelegationService getDelegationService() {
		return delegationService;
	}
	public EventService getEventService() {
		return eventService;
	}

	 private String authenticate(String identity, long ts) {
		String pt = identity + ts;
		Credentials cre = ProviderFactory.getProvider().getByAddress(identity);
		Signature sig = AuthenticationUtils.sig(pt, cre);
		
		try {
			String sgt = mapper.writeValueAsString(sig);
			
			return sgt;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}