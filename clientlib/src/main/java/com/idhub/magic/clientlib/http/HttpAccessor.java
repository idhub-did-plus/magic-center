package com.idhub.magic.clientlib.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;

public class HttpAccessor {
	static public String get(String identity, String url, Map<String, String> params) {
		long ts = System.currentTimeMillis();
		String sgt = authenticate(identity, ts);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("identity", identity));
		nvps.add(new BasicNameValuePair("timestamp", ts + ""));
		if (params != null) {

			for (String key : params.keySet()) {
				String k = key;

				nvps.add(new BasicNameValuePair(k, params.get(key)));
			}

		}
		HttpGet request = new HttpGet(url);
		request.addHeader("signature", sgt);
		try {
			URI uri = new URIBuilder(request.getURI()).addParameters(nvps).build();
			request.setURI(uri);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse resp = httpClient.execute(request);
			String json = EntityUtils.toString(resp.getEntity());
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	static public String post(String identity, String url, String body) {
		long ts = System.currentTimeMillis();
		String sgt = authenticate(identity, ts);
		HttpPost post = new HttpPost(url + "?identity=" + identity + "&timestamp=" + ts);
		post.addHeader("signature", sgt);

		try {
			StringEntity en = new StringEntity(body);
			post.setEntity(en);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse resp = httpClient.execute(post);
			String json = EntityUtils.toString(resp.getEntity());
			return json;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	static private String authenticate(String identity, long ts) {
		String pt = identity + ts;
		Credentials cre = ProviderFactory.getProvider().getByAddress(identity);
		Signature sig = AuthenticationUtils.sig(pt, cre);
		String sgt = JSON.toJSONString(sig);
		boolean authenticated = AuthenticationUtils.authenticate(sgt, identity + ts, identity);
		return sgt;
	}

}
