package com.idhub.magic.provider.service;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.mongodb.morphia.query.Query;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.utils.JsonUtils;
import com.idhub.magic.common.claim.VerifiableClaim;
import com.idhub.magic.common.util.JsonldUtils;
import com.idhub.magic.common.util.Signature;
import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.util.CanonicalizationUtil;
import com.idhub.magic.provider.ClaimConvertor;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.VerifiableClaimEntity;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;

public class ClaimUtils {
	static SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-DD");
	static public VerifiableClaimEntity issueClaim(String subject, String claimType,String country, String jurisdiction) throws Exception {
		VerifiableClaim claim = claim(subject, claimType, country, jurisdiction);
		VerifiableCredential cred = ClaimConvertor.to(claim);
		LdSignature lds = signature(cred);
		cred.getJsonLdObject().put("signature", lds.getJsonLdSignatureObject());
		String ld = JsonUtils.toString(cred.getJsonLdObject());
		return new VerifiableClaimEntity(claim, cred, ld);
		
			
	
	}
	
	static public VerifiableClaim claim(String subject, String claimType,String country, String jurisdiction) {
		
		VerifiableClaim claim = new VerifiableClaim();
		String[] cs = new String[] {"https://idhub.com/credentials/v1"};
		claim.getContext().addAll(Arrays.asList(cs));
		String did = subject;
		did = "did:" + "erc1056:" + did;
		claim.getClaim().setId(did);
		claim.getClaim().setClaimType(claimType);
		claim.getClaim().setCountry(country);
		claim.getClaim().setJurisdiction(jurisdiction);
		
		String issued = form.format(new Date());
		claim.setIssued(issued);
		String issuer = AccountManager.getMyAccount().getAddress();
		issuer = "did:" + "erc1056:" + issuer;
		claim.setIssuer(issuer);
		return claim;
			
	}
	static String encodeCononical(VerifiableCredential cred) throws Exception {
		String canonicalizedDocument = CanonicalizationUtil.buildCanonicalizedDocument(cred.getJsonLdObject());
		System.out.println(canonicalizedDocument);
			String plainMessage = JsonUtils.toString(canonicalizedDocument);
			return plainMessage;
			
	
	}
	static LdSignature signature(VerifiableCredential cred) throws Exception {
		String encoded = encodeCononical(cred) ;
		byte[] hexMessage = encoded.getBytes();
		Credentials credentials = AccountManager.getMyAccount();
		System.out.println(new String(hexMessage));
		ECKeyPair pair = credentials.getEcKeyPair();
		Sign.SignatureData signMessage = Sign.signMessage(hexMessage, pair);
		String sss = JsonldUtils.encodeSignature(signMessage.getR(), signMessage.getS(), signMessage.getV());
		/*
		 * String r = Numeric.toHexString(signMessage.getR()); String s =
		 * Numeric.toHexString(signMessage.getS());
		 * 
		 * int v = signMessage.getV()[0]; Signature sig = new Signature(r, s, v); String
		 * sss= new ObjectMapper().writeValueAsString(sig);
		 */
		LdSignature lds = new LdSignature();
		
		String created = form.format(new Date());
		URI creator = URI.create("did:erc1056:" + credentials.getAddress());
		String domain = null;
		String nonce = UUID.randomUUID().toString();
		lds.setCreated(created);
		lds.setCreator(creator);
		lds.setNonce(nonce);
		lds.setDomain(domain);
		lds.setType("ES256");
		lds.setSignatureValue(sss);
		return lds;
	}
	static public void main(String[] ss) {
		try {
		 VerifiableClaimEntity claim = issueClaim("9987778", "hhhh",  "us",  "us");
		 System.out.println(claim.getJsonld());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
