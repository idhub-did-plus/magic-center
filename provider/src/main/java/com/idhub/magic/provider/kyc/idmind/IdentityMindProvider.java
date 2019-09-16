package com.idhub.magic.provider.kyc.idmind;

import java.io.IOException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/*Perform a customer KYC.

Evaluate a consumer application for the provided user data:
POST https://edna.identitymind.com/im/account/consumer

Retrieve the current state of a consumer application:
GET https://edna.identitymind.com/im/account/consumer/<transaction_id>

Retrieve the current state of a consumer application using reseller credentials:
GET https://edna.identitymind.com/im/account/consumer/<merchant_api_name>/<transaction_id>

Upload a document as part of document verification:
GET https://edna.identitymind.com/im/account/consumer/<application_id>/addDocument
*/
@Service
public class IdentityMindProvider {
	static public String baseurl = "https://sandbox.identitymind.com";
	
	ObjectMapper mapper = new ObjectMapper();
	ConsumerService customerService;
	String password = "6bbbc50e3ce66e667998331e7f577967c2412e44";
	String username = "magicexchange";
	@PostConstruct
	public void init() {
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request();
				HttpUrl url = request.url();

				String auth = basicAuthentication();

				Request.Builder builder = request.newBuilder().addHeader("authorization", auth).url(url);
				return chain.proceed(builder.build());
			}
		}).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl)
				.addConverterFactory(JacksonConverterFactory.create(mapper)).client(client).build();

		customerService = retrofit.create(ConsumerService.class);
	}

	public ConsumerService getCustomerService() {
		return customerService;
	}

	String basicAuthentication() {
		String txt = username + ":" + password;
		String enc = Base64.getEncoder().encodeToString(txt.getBytes());
		return "Basic " + enc;
	}
}
