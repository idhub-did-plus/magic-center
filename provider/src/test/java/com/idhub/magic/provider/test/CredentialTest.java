package com.idhub.magic.provider.test;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.security.KeyFactory;
import java.util.Arrays;
import java.util.Base64;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.utils.JsonUtils;
import com.idhub.magic.common.claim.VerifiableClaim;
import com.idhub.magic.common.util.Signature;
import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.signer.EcdsaKoblitzSignature2016LdSigner;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;
import com.idhub.magic.ldsignatures.util.CanonicalizationUtil;
import com.idhub.magic.provider.ClaimConvertor;
import com.idhub.magic.provider.agent.AccountManager;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;


public class CredentialTest {
	static public void main(String[] ss) throws Exception {
		VerifiableClaim claim = new VerifiableClaim();
		String[] cs = new String[] {"https://trafi.fi/credentials/v1"};
		claim.getContext().addAll(Arrays.asList(cs));
		claim.getClaim().setId("did:sov:21tDAKCERh95uGgKbJNHYp");
		claim.getClaim().setClaimType("driver");
		claim.setIssued("2018-01-01");
		claim.setIssuer("did:sov:1yvXbmgPoUm4dl66D7KhyD");
	
			VerifiableCredential cred = ClaimConvertor.to(claim);

			URI creator = URI.create("did:sov:1yvXbmgPoUm4dl66D7KhyD#key1");
			String created = "2018-01-01T21:19:10Z";
			String domain = null;
			String nonce = "c0ae1c8e-c7e7-469f-b252-86e6a0e7387e";
			String canonicalizedDocument = CanonicalizationUtil.buildCanonicalizedDocument(cred.getJsonLdObject());
			String plainMessage = JsonUtils.toString(canonicalizedDocument);
			
			
	
			Credentials credentials = AccountManager.getMyAccount();
			
			byte[] hexMessage = plainMessage.getBytes();
			System.out.println(new String(hexMessage));
			ECKeyPair pair = credentials.getEcKeyPair();


		
		
			Sign.SignatureData signMessage = Sign.signMessage(hexMessage, pair);
			String r = Numeric.toHexString(signMessage.getR());
			String s = Numeric.toHexString(signMessage.getS());
			
			int v = signMessage.getV()[0];
			Signature sig =  new Signature(r, s, v);
			String sss= new ObjectMapper().writeValueAsString(sig);
			LdSignature lds = new LdSignature();
			lds.setCreated(created);
			lds.setCreator(creator);
			lds.setNonce(nonce);
			lds.setDomain(domain);
			lds.setType("ethereumec");
			lds.setSignatureValue(sss);
			cred.getJsonLdObject().put("signature", lds.getJsonLdSignatureObject());
			System.out.println(JsonUtils.toString(cred.getJsonLdObject()));
			
	
	}
}
