package com.idhub.magic.ldsignatures.validator;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERSequenceParser;
import org.web3j.crypto.ECKeyPair;

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

	public static boolean validate(String canonicalizedDocument, LdSignature ldSignature, ECKeyPair publicKey)
			throws GeneralSecurityException {

		// validate

		byte[] canonicalizedDocumentBytes = canonicalizedDocument.getBytes(StandardCharsets.UTF_8);
		byte[] signatureValueBytes = Base64.decodeBase64(ldSignature.getSignatureValue());

		// build the JWS and validate

		boolean validate;

		try {
			 ASN1StreamParser parser = new ASN1StreamParser(signatureValueBytes);
			 ASN1Encodable hh = parser.readObject();
			 ASN1Primitive p = hh.toASN1Primitive();
			 
			validate = publicKey.verify(Sha256Hash.hash(canonicalizedDocumentBytes), signatureValueBytes);
		} catch (SignatureDecodeException ex) {

			throw new GeneralSecurityException("Signature decoding problem: " + ex.getMessage(), ex);
		}

		// done

		return validate;
	}

	@Override
	public boolean validate(String canonicalizedDocument, LdSignature ldSignature) throws GeneralSecurityException {

		return validate(canonicalizedDocument, ldSignature, this.publicKey);
	}

	/*
	 * Getters and setters
	 */

	public ECKey getPublicKey() {

		return this.publicKey;
	}

	public void setPublicKey(ECKey publicKey) {

		this.publicKey = publicKey;
	}
}
