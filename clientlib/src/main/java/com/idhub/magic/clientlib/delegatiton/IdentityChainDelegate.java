package com.idhub.magic.clientlib.delegatiton;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.web3j.crypto.Credentials;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.Signature;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.http.HttpAccessor;
import com.idhub.magic.clientlib.interfaces.IdentityChain;

public class IdentityChainDelegate implements IdentityChain{

	public void createIdentity(String recovery, String associate, List<String> providers, List<String> resolvers) {

		CreateIdentityDelegatedParam rst = new CreateIdentityDelegatedParam();
		rst.associatedAddress = associate;
		rst.recoveryAddress = recovery;
		rst.providers = providers;
		rst.resolvers = resolvers;

		rst.timestamp = Numeric.toHexString(BigInteger.valueOf(System.currentTimeMillis() / 1000).toByteArray());
		rst = ClientEncoder.encode(rst);
		String url = "http://localhost:8080/delegation/createIdentity";
		String body = JSON.toJSONString(rst);
		HttpAccessor.post(associate, url, body);	

	}

	static CreateIdentityDelegatedParam param(String recovery, String associate, List<String> providers, List<String> resolvers) {
		CreateIdentityDelegatedParam rst = new CreateIdentityDelegatedParam();
		rst.associatedAddress = associate;
		rst.recoveryAddress = recovery;
		rst.providers = providers;
		rst.resolvers = resolvers;

		rst.timestamp = Numeric.toHexString(BigInteger.valueOf(System.currentTimeMillis() / 1000).toByteArray());
		rst = ClientEncoder.encode(rst);
		return rst;

	}

}
