package com.idhub.magic.provider.acl.controller;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import javax.servlet.http.HttpSession;


//@Service("authorizer")
public class SmartRealm extends AuthorizingRealm {
	public SmartRealm() {
		super();
		this.setAuthenticationTokenClass(SignatureToken.class);
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		info.addRole("lawer");

		return info;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		SignatureToken token = (SignatureToken) authcToken;
		String[] credentials = (String[]) token.getCredentials();
		String plain = credentials[0];
		byte[] data = null;
		try {
			data = plain.getBytes("UTF8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//Numeric.hexStringToByteArray(plain);
		String rsv = credentials[1];
		byte[] signature = Numeric.hexStringToByteArray(rsv);
		byte[] r = copy(signature, 0, 32);
		byte[] s = copy(signature, 32, 32);
		byte[] v = copy(signature, 64, 1);
		SignatureData sig = new Sign.SignatureData(v, r, s);
		String pubKey;
		try {
			byte[] hash = Hash.sha3(data);
			pubKey = Sign.signedMessageToKey(data, sig).toString(16);
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AuthenticationException(e);
		}
			String signerAddress = "0x" + Keys.getAddress(pubKey);
			String identity = (String) token.getPrincipal();
			boolean rst = signerAddress.equalsIgnoreCase(identity);
			if(!rst)
				throw new AuthenticationException("auth failed!");
			return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
		

		
	}
	static byte[] copy(byte[] data, int from, int size) {
		byte[] rst = new byte[size];
		for (int i = 0; i < size; i++)
			rst[i] = data[i + from];
		return rst;
	}
	
   
}
