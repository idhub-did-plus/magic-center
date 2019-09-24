package com.idhub.magic.center.util;

import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtTokenUtil {
	static ObjectMapper mapper = new ObjectMapper();

	static public boolean validate(String token) {
		try {
			String[] hpse = token.split("\\.");

			String header = new String(Base64.decodeBase64(hpse[0].getBytes()));
			String payload = new String(Base64.decodeBase64(hpse[1].getBytes()));

			Header headero = mapper.readValue(header, Header.class);
			Payload payloado = mapper.readValue(payload, Payload.class);
			String plain = hpse[0] + "." + hpse[1];

			byte[] signature = Base64.decodeBase64(hpse[2].getBytes());
			byte[] r = copy(signature, 0, 32);
			byte[] s = copy(signature, 32, 32);
			byte[] v = copy(signature, 64, 1);
			SignatureData sig = new Sign.SignatureData(v, r, s);
			String pubKey = Sign.signedMessageToKey(plain.getBytes(), sig).toString(16);

			String signerAddress = "0x" + Keys.getAddress(pubKey);
			boolean rst = signerAddress.equalsIgnoreCase(payloado.iss);
			return rst;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	static byte[] copy(byte[] data, int from, int size) {
		byte[] rst = new byte[size];
		for (int i = 0; i < size; i++)
			rst[i] = data[i + from];
		return rst;
	}

	static public void main(String[] ss) {
		String token = "eyJhbGciOiJFUzI1NmsiLCJ0eXAiOiJKV1QifQ==.eyJhdWQiOiJkaWQ6aWRodWI6MHhkaWQxZDhmOTA2Yzc0NWIwYTgyZjRkMjFlMDJiYWZkN2RmMWEwZTE0Iiwic3ViIjoibG9naW58YXV0aG9yaXphdGlvbnxzdHxkaWR8YXJjaGl2ZSIsInVybCI6Imh0dHA6XC9cL2xvY2FsaG9zdDo4MDgwXC9zdC1wcm9qZWN0XC8jXC9xcmNvZGUiLCJyZHQiOiJodHRwOlwvXC8xMC4xNi4zNC4yNDozMDA1XC92MVwvZGlkXC90b2tlblwvIiwianRpIjoiNDNjMTJlNTctN2NkYi00Mzg1LWFhZWUtNjNiNGJjMzEzZjJlIiwiZXhwIjoxNTY5MjkxNzI4LCJpc3MiOiIweDE0YmMzMDk2ZmFlZjdhMDExZmZkNjU1ZjRhMGZkMmI1MzRjMDlkMTkifQ==.NOKzlA3aaCwXHIbhKKX4fWO7J4Jd6PfKFVqeoN4_5nlxJEFXJTWgYasXXCdsA6eKgPhzPMACsJrwr6m8vLaEnRw=";
		validate(token);
	}

	/*
	 * void bb() { byte[] pk =
	 * AccountManager.getClient().getEcKeyPair().getPrivateKey().toByteArray();
	 * KeyFactory kf = KeyFactory.getInstance("EC256"); // or "EC" or whatever
	 * ECPrivateKey privateKey = (ECPrivateKey) kf.generatePrivate(new
	 * PKCS8EncodedKeySpec(pk)); Algorithm algorithmHS = Algorithm.ECDSA256(null,
	 * privateKey); String token = JWT.create() .withSubject("") .withExpiresAt(new
	 * Date()) .withNotBefore(new Date()) .withIssuer("issuer") .withClaim("roles",
	 * "b") .withClaim("keys", "b") .sign(algorithmHS);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * System.out.println(token); }
	 */
}

class Header {

	public String alg;
	public String typ;
}

class Payload {
	public String aud;
	public String sub;
	public String url;
	public String rdt;
	public String jti;
	public long exp;
	public String iss;
}