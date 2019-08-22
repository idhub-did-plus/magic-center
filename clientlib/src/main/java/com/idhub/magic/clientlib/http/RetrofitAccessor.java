package com.idhub.magic.clientlib.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.FinancialProfile;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.IdentityInfo;
import com.idhub.magic.center.ustorage.entity.Material;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.EventService;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class RetrofitAccessor {
	 public RetrofitAccessor() {
		super();
		init();
		// TODO Auto-generated constructor stub
	}
	IdentityStorage identityStorage;
	EventService eventService;
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
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
				.create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080")
				.addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(gson)).client(client).build();

		identityStorage = retrofit.create(IdentityStorage.class);
		eventService = retrofit.create(EventService.class);

	}

	public EventService getEventService() {
		return eventService;
	}

	static private String authenticate(String identity, long ts) {
		String pt = identity + ts;
		Credentials cre = ProviderFactory.getProvider().getByAddress(identity);
		Signature sig = AuthenticationUtils.sig(pt, cre);
		String sgt = JSON.toJSONString(sig);
		boolean authenticated = AuthenticationUtils.authenticate(sgt, identity + ts, identity);
		return sgt;
	}
	static public void main(String[] ss) throws Exception {
			RetrofitAccessor ra = new RetrofitAccessor();
			String identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
			
			EventService ser = ra.getEventService();
			List<ChainEvent> rst = ser.queryEvents(identity);
			
			
			
			IdentityArchive ida = new IdentityArchive();
			IdentityInfo ii = new IdentityInfo();
			ii.setBirthday(new Date());
			ii.setCountry("china");
			ii.setFirstName("yuqi");
			ii.setLastName("bai");
			ii.setPassportNumber("ggggg");
			ida.setIdentityInfo(ii);
		    //   RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), ida);
			MagicResponse mss = ra.getIdentityStorage().storeArchive(ida, identity).execute().body();
			   mss = ra.getIdentityStorage().storeFinancialProfile(new FinancialProfile(), identity).execute().body();
		//	  MagicResponse ms = ra.getIdentityStorage().removeMaterial(identity, "kkk", "lll").execute().body();
			// MagicResponse<IdentityArchive> user = ra.getIdentityStorage().retrieveArchive(identity).execute().body();
		//	 System.out.println(user);
			  File file = new File("c:\\timg.jpg");
			  MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
			  ra.getIdentityStorage().uploadMaterial(identity, "ddd", "ddd", filePart).execute().body();
	
	}
}