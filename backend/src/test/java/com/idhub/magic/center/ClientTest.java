package com.idhub.magic.center;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.ContractUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.assertj.core.util.Preconditions;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.account.AccountManager;
import com.idhub.magic.center.contracts.IdentityRegistryInterface;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.CryptoUtil;
import com.idhub.magic.center.util.Signature;

public class ClientTest {

	public static void main(String[] args) {

		long ts = System.currentTimeMillis();
		String id = AccountManager.getClient().getAddress();
		String pt = id + ts;
		Signature sig = AuthenticationUtils.sig(pt);
		String sgt = JSON.toJSONString(sig);
		boolean authenticated = AuthenticationUtils.authenticate(sgt, id + ts, id);
		System.out.println(authenticated);
		HttpPost post = new HttpPost(
				"http://localhost:8080/delegation/createIdentity?identity=" + id + "&timestamp=" + ts);
		post.addHeader("signature", sgt);
		try {

			String body = JSON.toJSONString(param());
			StringEntity en = new StringEntity(body);
			post.setEntity(en);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse resp = httpClient.execute(post);
			System.out.print(resp.getStatusLine());
		} catch (ClientProtocolException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static CreateIdentityDelegatedParam param() {
		CreateIdentityDelegatedParam rst = new CreateIdentityDelegatedParam();
		Credentials credentials = AccountManager.getClient();

		String id = credentials.getAddress();

		List<String> resolvers = new ArrayList<String>();
		resolvers.add(DeployedContractAddress.ERC1056ResolverInterface);
		List<String> providers = new ArrayList<String>();

		rst.associatedAddress = id;
		rst.recoveryAddress = id;
		rst.providers = providers;
		rst.resolvers = resolvers;
		rst.timestamp = Numeric.toHexString(BigInteger.valueOf(System.currentTimeMillis() / 1000).toByteArray());
		rst = ClientEncoder.encode(rst);
		return rst;

	}

}
