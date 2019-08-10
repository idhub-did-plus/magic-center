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
import org.web3j.tuples.generated.Tuple4;
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


import com.alibaba.fastjson.JSON;

import com.idhub.magic.center.contracts.IdentityRegistryInterface;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.CryptoUtil;
import com.idhub.magic.center.util.Signature;

public class LocalGetTest {

	public static void main(String[] args) throws Exception {

		Credentials credentials = AccountManager.getClient();

		

		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
		IdentityRegistryInterface registry1484 = IdentityRegistryInterface
				.load(DeployedContractAddress.IdentityRegistryInterface, web3j, credentials, contractGasProvider);

		Tuple4<String, List<String>, List<String>, List<String>> data = registry1484.getIdentity(BigInteger.valueOf(1)).send();
		System.out.println(data);

	}

}
