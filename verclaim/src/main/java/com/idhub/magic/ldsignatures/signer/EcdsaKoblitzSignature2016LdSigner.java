package com.idhub.magic.ldsignatures.signer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSequenceGenerator;
import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import com.idhub.magic.ldsignatures.suites.EcdsaKoblitzSignature2016SignatureSuite;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;

public class EcdsaKoblitzSignature2016LdSigner extends LdSigner<EcdsaKoblitzSignature2016SignatureSuite> {

	private 	ECKeyPair keypair;

	public EcdsaKoblitzSignature2016LdSigner() {

		super(SignatureSuites.SIGNATURE_SUITE_ECDSAKOBLITZSIGNATURE2016);
	}

	public EcdsaKoblitzSignature2016LdSigner(URI creator, String created, String domain, String nonce,
			ECKeyPair kp) {

		super(SignatureSuites.SIGNATURE_SUITE_ECDSAKOBLITZSIGNATURE2016, creator, created, domain, nonce);

		this.keypair = kp;
	}
	 static  protected ByteArrayOutputStream derByteStream(byte[] r, byte[] s, byte[] v) {
          try {
			// Usually 70-72 bytes.
			  ByteArrayOutputStream bos = new ByteArrayOutputStream(72);
			  DERSequenceGenerator seq = new DERSequenceGenerator(bos);
			  seq.addObject(new ASN1Integer(r));
			  seq.addObject(new ASN1Integer(s));
			  seq.addObject(new ASN1Integer(v));
			  seq.close();
			  return bos;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
      }
	public static String sign(String canonicalizedDocument, ECKeyPair kp ) throws GeneralSecurityException {

		// sign

		byte[] canonicalizedDocumentBytes = canonicalizedDocument.getBytes(StandardCharsets.UTF_8);
		Sign.SignatureData signMessage = Sign.signMessage(canonicalizedDocumentBytes, kp);
		derByteStream(signMessage.getR(), signMessage.getS(), signMessage.getV()).toByteArray();
		byte[] signatureBytes = derByteStream(signMessage.getR(), signMessage.getS(), signMessage.getV()).toByteArray();
		String signatureString = Base64.encodeBase64String(signatureBytes);

		// done

		return signatureString;
	}

	@Override
	public String sign(String canonicalizedDocument) throws GeneralSecurityException {

		return sign(canonicalizedDocument, this.keypair);
	}

	/*
	 * Getters and setters
	 */
	static public void main(String[] ss) throws Exception {
		byte[] data = derByteStream(new byte[] {1,2,3}, new byte[] {3,5,7}, new byte[] {6}).toByteArray();
		
		 ASN1StreamParser parser = new ASN1StreamParser(data);
		 ASN1Encodable hh = parser.readObject();
		 DERSequence p = (DERSequence) hh.toASN1Primitive();
		 ASN1Encodable[] as = p.toArray();
		 for(ASN1Encodable i  : as) {
			 ASN1Integer ii = (ASN1Integer)i;
			byte[] s =  ii.getEncoded();
			System.out.println(s);
		 }
	}
	
}
