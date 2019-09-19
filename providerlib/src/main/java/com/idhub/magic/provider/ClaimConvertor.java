package com.idhub.magic.provider;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.github.jsonldjava.utils.JsonUtils;
import com.idhub.magic.common.claim.Claim;
import com.idhub.magic.common.claim.Signature;
import com.idhub.magic.common.claim.VerifiableClaim;

import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.signer.RsaSignature2018LdSigner;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;



public class ClaimConvertor {
	static public VerifiableClaim from(VerifiableCredential cred) {
		VerifiableClaim rst = new VerifiableClaim();
		List cs = cred.getContext();
		for(Object o : cs) {
			rst.getContext().add(o.toString());
		}
		rst.setIssued(cred.getIssued());
		rst.setIssuer(cred.getIssuer().toString());
		rst.setId(cred.getId().toString());
		LinkedHashMap<String, Object> cl = cred.getJsonLdClaimsObject();
		rst.getClaim().setId(cl.get("id").toString());
		Object claims = cl.get("claimType");
		rst.getClaim().setClaimType(claims.toString());
		LdSignature sig = cred.getLdSignature();
		rst.getSignature().setCreated(sig.getCreated());
		rst.getSignature().setCreator(sig.getCreator().toString());
		rst.getSignature().setDomain(sig.getDomain());
		rst.getSignature().setNonce(sig.getNonce());
		rst.getSignature().setSignatureValue(sig.getSignatureValue());
		rst.getSignature().setType(sig.getType());
		return rst;
	}
	static public VerifiableCredential to(VerifiableClaim cred) throws Exception {
		VerifiableCredential rst = new VerifiableCredential();
		
		List<String> cs = cred.getContext();
		for(Object o : cs) {
			rst.getContext().add(new URI(o.toString()));
		}
		rst.setIssued(cred.getIssued());
		rst.setIssuer(new URI(cred.getIssuer()));
		if(cred.getId()!= null)
			rst.setId(new URI(cred.getId()));
		LinkedHashMap<String, Object> cl = new LinkedHashMap<String, Object>();
		Claim c = cred.getClaim();
		rst.getJsonLdClaimsObject().put("id", c.getId());
		rst.getJsonLdClaimsObject().put("claimType", c.getClaimType());
		Signature sig = cred.getSignature();
		if(sig.getSignatureValue() == null)
			return rst;
		rst.getLdSignature().setCreated(sig.getCreated());
		rst.getLdSignature().setCreator(new URI(sig.getCreator()));
		rst.getLdSignature().setDomain(sig.getDomain());
		rst.getLdSignature().setNonce(sig.getNonce());
		rst.getLdSignature().setSignatureValue(sig.getSignatureValue());
		rst.getLdSignature().setType(sig.getType());
		return rst;
	}
	static public void main(String[] ss) {
		VerifiableClaim claim = new VerifiableClaim();
		String[] cs = new String[] {"https://w3id.org/credentials/v1", "https://trafi.fi/credentials/v1", "https://w3id.org/security/v1"};
		claim.getContext().addAll(Arrays.asList(cs));
		claim.getClaim().setId("did:sov:21tDAKCERh95uGgKbJNHYp");
		claim.getClaim().setClaimType("driver");
		claim.setIssued("2018-01-01");
		claim.setIssuer("did:sov:1yvXbmgPoUm4dl66D7KhyD");
	
	
		try {
			VerifiableCredential cred = to(claim);

			URI creator = URI.create("did:sov:1yvXbmgPoUm4dl66D7KhyD#key1");
			String created = "2018-01-01T21:19:10Z";
			String domain = null;
			String nonce = "c0ae1c8e-c7e7-469f-b252-86e6a0e7387e";
			
			/*
			 * String pri =
			 * "0f2e67d493a271e2421929cb56f58bce05b27c081f18ab1b9491b4394e0116a2";
			 * KeyFactory keyFactory = KeyFactory.getInstance("RSA"); //
			 * keyFactory.generatePrivate(spec); // RsaSignature2018LdSigner signer = new
			 * RsaSignature2018LdSigner(creator, created, domain, nonce,
			 * TestUtil.testRSAPrivateKey); LdSignature ldSignature =
			 * signer.sign(cred.getJsonLdObject());
			 * 
			 * assertEquals(SignatureSuites.SIGNATURE_SUITE_RSASIGNATURE2018.getTerm(),
			 * ldSignature.getType()); assertEquals(creator, ldSignature.getCreator());
			 * assertEquals(created, ldSignature.getCreated()); assertEquals(domain,
			 * ldSignature.getDomain()); assertEquals(nonce, ldSignature.getNonce());
			 */
			
			
			System.out.println(JsonUtils.toString(cred.getJsonLdObject()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
