package com.idhub.magic.center;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.ContractUtils;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.assertj.core.util.Preconditions;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.crypto.CryptoManager;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.CryptoUtil;
import com.idhub.magic.center.util.Signature;
public class ClientTest {
	
	public static void main(String[] args) {

		  long ts = System.currentTimeMillis();
		  String id = CryptoManager.getCredentials().getAddress();
		  String pt = id + ts;
		  Signature sig = AuthenticationUtils.sig(pt);
		  String sgt = JSON.toJSONString(sig);
		  boolean authenticated = AuthenticationUtils.authenticate(sgt, id + ts, id);
		  System.out.println(authenticated);
		  HttpPost post =  new HttpPost("http://localhost:8080/delegation/createIdentity?identity=" + id + "&timestamp=" + ts);
		  post.addHeader("signature", sgt);
		  try {
		  StringEntity en = new StringEntity(sgt);
		  post.setEntity(en);
		  CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse resp = httpClient.execute(post);
			System.out.print(resp.getStatusLine());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		   byte[] permissionString = CryptoUtil.soliditySha3(
			        "0x19", "0x00", DeployedContractAddress.IdentityRegistryInterface,
			        "I authorize the creation of an Identity on my behalf.",
			        null,
			        null,
			        null, null, null
			       
			      );
		 
		  CreateIdentityDelegatedParam p = new CreateIdentityDelegatedParam();
		  
		  
		  
	      

	}

}
