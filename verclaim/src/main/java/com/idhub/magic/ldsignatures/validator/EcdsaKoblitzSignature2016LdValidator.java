package com.idhub.magic.ldsignatures.validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSequenceParser;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.suites.EcdsaKoblitzSignature2016SignatureSuite;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;

public class EcdsaKoblitzSignature2016LdValidator extends LdValidator<EcdsaKoblitzSignature2016SignatureSuite> {

	private 	ECKeyPair keypair;

	public EcdsaKoblitzSignature2016LdValidator() {

		super(SignatureSuites.SIGNATURE_SUITE_ECDSAKOBLITZSIGNATURE2016);
	}

	public EcdsaKoblitzSignature2016LdValidator(ECKeyPair publicKey) {

		super(SignatureSuites.SIGNATURE_SUITE_ECDSAKOBLITZSIGNATURE2016);

		this.keypair = publicKey;
	}

	public static boolean validate(String canonicalizedDocument, LdSignature ldSignature, ECKeyPair publicKey, String issuer)
			throws GeneralSecurityException {

		// validate

		byte[] canonicalizedDocumentBytes = canonicalizedDocument.getBytes(StandardCharsets.UTF_8);
		byte[] signatureValueBytes = Base64.decodeBase64(ldSignature.getSignatureValue());

		// build the JWS and validate

		boolean validate;
		byte[] data = Base64.decodeBase64(ldSignature.getSignatureValue());
	
			List<byte[]> rsv = parse(data);
			
		        SignatureData sig = new Sign.SignatureData(rsv.get(3),rsv.get(0), rsv.get(1));
		    	String pubKey;
				try {
					pubKey = Sign.signedMessageToKey(canonicalizedDocumentBytes, sig).toString(16);
				
					String signerAddress = "0x" + Keys.getAddress(pubKey);
					
					return signerAddress.equalsIgnoreCase(issuer);
				} catch (SignatureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			
		
	
	}
	static public List<byte[]> parse(byte[] data) {
		
		try {
			List<byte[]> rst = new ArrayList<byte[]>();
			 ASN1StreamParser parser = new ASN1StreamParser(data);
			 ASN1Encodable hh = parser.readObject();
			 DERSequence p = (DERSequence) hh.toASN1Primitive();
			 ASN1Encodable[] as = p.toArray();
			 for(ASN1Encodable i  : as) {
				 ASN1Integer ii = (ASN1Integer)i;
				byte[] s =  ii.getEncoded("DER");
				
				byte[] e = get(s);
				rst.add(s);
			 }
			 return rst;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	static byte[] get(byte[] data) {
		int length = data[1];
		byte[] rst = new byte[length];
		for(int i = 0; i < length; i++)
			rst[i] = data[i + 2];
		return rst;
	}

	@Override
	public boolean validate(String canonicalizedDocument, LdSignature ldSignature) throws GeneralSecurityException {
		String issuer = ldSignature.getCreator().toString();
		issuer = issuer.split("\\.")[2];
		return validate(canonicalizedDocument, ldSignature, this.keypair,issuer);
	}

	
}
