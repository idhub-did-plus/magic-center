package com.idhub.magic.clientlib.local;

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
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.IdentityChain;

public class IdentityChainLocal implements IdentityChain {
	public void createIdentity(String recovery, String associate, List<String> providers, List<String> resolvers) {
		Credentials credentials = ProviderFactory.getProvider().getByAddress(associate);


		

		TransactionReceipt data;
		try {
			data = ContractManager.getRegistry1484().createIdentity(recovery, providers, resolvers).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
