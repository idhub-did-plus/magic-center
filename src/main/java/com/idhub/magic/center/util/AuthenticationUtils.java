package com.idhub.magic.center.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.crypto.CryptoManager;

public class AuthenticationUtils {

	
	static public Signature sig(String plainMessage) {
		
		byte[] hexMessage = Hash.sha3(plainMessage.getBytes());
		
		Credentials credentials = CryptoManager.getCredentials();
		ECKeyPair pair = credentials.getEcKeyPair();


	
	
		Sign.SignatureData signMessage = Sign.signMessage(hexMessage, pair);
		String r = Base64.getEncoder().encodeToString(signMessage.getR());
		String s = Base64.getEncoder().encodeToString(signMessage.getS());
		
	
		int v = signMessage.getV();
		return new Signature(r, s, v);
	}
	
	public static boolean authenticate(String signature, String original, String address) {
		Signature sig = JSON.parseObject(signature, Signature.class);
		byte v = (byte) sig.v;
		byte[] r = Base64.getDecoder().decode(sig.r);
		
		byte[] s = Base64.getDecoder().decode(sig.s);
		
		byte[] hexMessage = Hash.sha3(original.getBytes());
		
		Sign.SignatureData signMessage = new Sign.SignatureData((byte)sig.v, r, s);
		
		
		String pubKey;
		try {
			pubKey = Sign.signedMessageToKey(hexMessage, signMessage).toString(16);
		
			String signerAddress = "0x" + Keys.getAddress(pubKey);
			
			return signerAddress.equalsIgnoreCase(address);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}


}
