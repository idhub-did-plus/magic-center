package com.idhub.magic.provider;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;

import com.idhub.magic.common.claim.Claim;
import com.idhub.magic.common.claim.Signature;
import com.idhub.magic.common.claim.VerifiableClaim;
import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;



public class ClaimConvertor {
	static public VerifiableClaim from(VerifiableCredential cred) {
		VerifiableClaim rst = new VerifiableClaim();
		List cs = cred.getContext();
		for(Object o : cs) {
			rst.getContext().add(o.toString());
		}
		rst.setIssued(cred.getIssued());
		rst.setExpires(cred.getExpires());
		rst.setIssuer(cred.getIssuer().toString());
		rst.setId(cred.getId().toString());
		rst.setSubject(cred.getSubject());
		LinkedHashMap<String, Object> cl = cred.getJsonLdClaimsObject();
		rst.getClaim().setId(cl.get("id").toString());
		Object claims = cl.get("claimType");
		Object country = cl.get("country");
		Object jurisdiction = cl.get("jurisdiction");
		rst.getClaim().setCountry(country.toString());
		rst.getClaim().setJurisdiction(jurisdiction.toString());
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
	static public VerifiableCredential to(VerifiableClaim cred) {
		VerifiableCredential rst = new VerifiableCredential();
		

		for(Object o : cred.getContext()) {
			rst.getContext().add(o);
		}
	
		try {
			rst.setIssued(cred.getIssued());
			rst.setIssuer(new URI(cred.getIssuer()));
			rst.setExpires(cred.getExpires());
			rst.setSubject(cred.getSubject());
			if(cred.getId()!= null)
				rst.setId(new URI(cred.getId()));
			LinkedHashMap<String, Object> cl = new LinkedHashMap<String, Object>();
			Claim c = cred.getClaim();
			rst.getJsonLdClaimsObject().put("id", c.getId());
			rst.getJsonLdClaimsObject().put("claimType", c.getClaimType());
			rst.getJsonLdClaimsObject().put("country", c.getCountry());
			rst.getJsonLdClaimsObject().put("jurisdiction", c.getJurisdiction());
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
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
