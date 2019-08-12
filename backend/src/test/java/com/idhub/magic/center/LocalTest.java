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
import com.idhub.magic.center.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.center.util.AuthenticationUtils;
import com.idhub.magic.center.util.CryptoUtil;
import com.idhub.magic.center.util.Signature;

public class LocalTest {

	public static void main(String[] args) throws Exception {

		Credentials credentials = AccountManager.getClient();

		String id = credentials.getAddress();
		Address address = new Address(id);

		long ts = System.currentTimeMillis();
		ts = ts / 1000;
		BigInteger tst = BigInteger.valueOf(ts);

		byte[] hexMessage = CryptoUtil.encodePacked((byte) 0x19, (byte) 0x00,
				new Address(DeployedContractAddress.IdentityRegistryInterface),
				"I authorize the creation of an Identity on my behalf.", address, address,
				Numeric.toBigInt(DeployedContractAddress.ERC1056ResolverInterface), tst

		);
		// hexMessage =
		// Numeric.hexStringToByteArray("0xeff3c26e0428bead656787146bbd078bb5563305d38e935c7f15738815e1367a");
		System.out.println(Numeric.toHexString(hexMessage));
		ECKeyPair pair = credentials.getEcKeyPair();

		Sign.SignatureData signMessage = Sign.signMessage(hexMessage, pair);

		List<String> rss = new ArrayList<String>();
		rss.add(DeployedContractAddress.ERC1056ResolverInterface);
		List<String> pss = new ArrayList<String>();

		ContractGasProvider contractGasProvider = new DefaultGasProvider();
		Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
		IdentityRegistryInterface registry1484 = IdentityRegistryInterface
				.load(DeployedContractAddress.IdentityRegistryInterface, web3j, credentials, contractGasProvider);
		BigInteger ein = registry1484.getEIN("0x458b6862cac349a47658ef7251f22054ffa0d4ed").send();
		byte[] data = registry1484.encode(credentials.getAddress(), credentials.getAddress(), pss, rss, tst).send();
		System.out.println(Numeric.toHexString(data));
		data = registry1484.hash(credentials.getAddress(), credentials.getAddress(), pss, rss, tst).send();
		System.out.println(Numeric.toHexString(data));

		registry1484.createIdentityDelegated(credentials.getAddress(), credentials.getAddress(), pss, rss,
				BigInteger.valueOf(signMessage.getV()), signMessage.getR(), signMessage.getS(), tst).send();

	}

}
