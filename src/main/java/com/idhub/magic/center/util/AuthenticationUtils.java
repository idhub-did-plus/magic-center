package com.idhub.magic.center.util;

import java.security.SignatureException;
import java.util.Base64;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.account.AccountManager;

public class AuthenticationUtils {

	
	static public Signature sig(String plainMessage) {
		
		byte[] hexMessage = Hash.sha3(plainMessage.getBytes());
		
		Credentials credentials = AccountManager.getCredentials();
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
