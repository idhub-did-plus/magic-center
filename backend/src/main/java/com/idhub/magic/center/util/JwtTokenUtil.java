package com.idhub.magic.center.util;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.idhub.magic.center.account.AccountManager;


@Component
public class JwtTokenUtil implements Serializable {
	
	
	static public void main(String[] ss) throws Exception {
		
		 byte[] pk = AccountManager.getClient().getEcKeyPair().getPrivateKey().toByteArray();
		 KeyFactory kf = KeyFactory.getInstance("EC256"); // or "EC" or whatever
		 ECPrivateKey privateKey = (ECPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(pk));
		    Algorithm algorithmHS = Algorithm.ECDSA256(null, privateKey);
		    String token = JWT.create()
		        .withSubject("")
		        .withExpiresAt(new Date())
		        .withNotBefore(new Date())
		        .withIssuer("issuer")
		        .withClaim("roles", "b")
		        .withClaim("keys", "b")
		        .sign(algorithmHS);
		
		
		
		
		
		System.out.println(token);
	}
}