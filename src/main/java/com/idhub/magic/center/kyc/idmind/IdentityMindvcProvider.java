package com.idhub.magic.center.kyc.idmind;

import java.util.Base64;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import com.idhub.magic.center.http.HttpClientManager;
import com.idhub.magic.center.kyc.idmind.entity.customer.CustomerKycResponse;
/*Perform a customer KYC.

Evaluate a consumer application for the provided user data:
POST https://edna.identitymind.com/im/account/consumer

Retrieve the current state of a consumer application:
GET https://edna.identitymind.com/im/account/consumer/<transaction_id>

Retrieve the current state of a consumer application using reseller credentials:
GET https://edna.identitymind.com/im/account/consumer/<merchant_api_name>/<transaction_id>

Upload a document as part of document verification:
GET https://edna.identitymind.com/im/account/consumer/<application_id>/addDocument
*/public class IdentityMindvcProvider {
	String password;
	String username;
	public CustomerKycResponse request() {
		CloseableHttpClient client = HttpClientManager.getHttpClient();
		HttpPost post =  new HttpPost("https://edna.identitymind.com/im/account/consumer");
		String auth = basicAuthentication();
		 post.addHeader("Authorization", auth);
		 
		 
		  
		return null;
	}
	String basicAuthentication(){
		String txt = username + ":" + password;
		String enc = Base64.getEncoder().encodeToString(txt.getBytes());
		return "basic " + enc;
	}
}
