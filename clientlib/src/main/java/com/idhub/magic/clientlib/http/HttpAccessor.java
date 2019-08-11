package com.idhub.magic.clientlib.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;

public class HttpAccessor {
	static public <T> T get(String identity, String url, Map<String, String> params){
		return null;
	}
	static public <T> T post(String identity, String url, String body){
		long ts = System.currentTimeMillis();
		String sgt = authenticate(identity, ts);
		HttpPost post = new HttpPost(url + "?identity=" + identity + "&timestamp=" + ts);
		post.addHeader("signature", sgt);

		try {
			StringEntity en = new StringEntity(body);
			post.setEntity(en);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse resp = httpClient.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		return null;
		
	}
	static private String authenticate(String identity, long ts) {
		String pt = identity + ts;
		Credentials cre = ProviderFactory.getProvider().getByAddress(identity);
		Signature sig = AuthenticationUtils.sig(pt,cre);
		String sgt = JSON.toJSONString(sig);
		boolean authenticated = AuthenticationUtils.authenticate(sgt, identity + ts, identity);
		return sgt;
	}
	
}
