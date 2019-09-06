package com.idhub.magic.provider.agent;

import java.io.IOException;
import java.util.Base64;

import org.web3j.crypto.Credentials;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.provider.kyc.idmind.ConsumerService;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OrderBookFactory {
static public String baseurl = "https://sandbox.identitymind.com";
	
	ObjectMapper mapper = new ObjectMapper();
	OrderBookAgent orderBook;


	public void init() {
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				HttpUrl url = request.url();
				
				long tp = System.currentTimeMillis();
				long ts = System.currentTimeMillis();
				String sgt = authenticate( ts);
				String nurl = url.url().toString() + "&timestamp=" + ts;

				Request.Builder builder = request.newBuilder().addHeader("signature", sgt).url(nurl);
				return chain.proceed(builder.build());
			}
		}).build();
		

		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl)
				.addConverterFactory(JacksonConverterFactory.create(mapper)).client(client).build();

		orderBook = retrofit.create(OrderBookAgent.class);
	}

	
	 public OrderBookAgent getOrderBook() {
		return orderBook;
	}


	private String authenticate( long ts) {
		
		Credentials cre = AccountManager.getMyAccount();
		String identity = cre.getAddress();
		String pt = identity + ts;
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
