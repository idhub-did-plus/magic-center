package com.idhub.magic.clientlib.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
				.addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()).client(client).build();

		identityStorage = retrofit.create(IdentityStorage.class);


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
			 MagicResponse user = ra.getIdentityStorage().storeArchive(new IdentityArchive(), identity).execute().body();
			 System.out.println(user);
	
	}
}