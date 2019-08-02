package com.idhub.magic.center;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.center.util.Utils;

public class ClientTest {

	public static void main(String[] args) {
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  long ts = System.currentTimeMillis();
		  String id = "0xb9c5714089478a327f09197987f16f9e5d936e8a";
		  String pt = id + ts;
		  Signature sig = Utils.sig(pt);
		  String sgt = JSON.toJSONString(sig);
		  HttpPost post =  new HttpPost("http://localhost:8080/proxy/createIdentity?identity=" + id + "&timestamp=" + ts);
		  post.addHeader("signature", sgt);
		  try {
			httpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	      

	}

}
