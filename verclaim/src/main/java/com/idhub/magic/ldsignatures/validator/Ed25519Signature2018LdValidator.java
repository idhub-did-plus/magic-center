package com.idhub.magic.ldsignatures.validator;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import org.apache.commons.codec.binary.Base64;

import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.crypto.EC25519Provider;
import com.idhub.magic.ldsignatures.suites.Ed25519Signature2018SignatureSuite;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;

public class Ed25519Signature2018LdValidator extends LdValidator<Ed25519Signature2018SignatureSuite> {

	private byte[] publicKey;

	public Ed25519Signature2018LdValidator() {

		super(SignatureSuites.SIGNATURE_SUITE_ED25519SIGNATURE2018);
	}

	public Ed25519Signature2018LdValidator(byte[] publicKey) {

		super(SignatureSuites.SIGNATURE_SUITE_ED25519SIGNATURE2018);

		this.publicKey = publicKey;
	}

	public static boolean validate(String canonicalizedDocument, LdSignature ldSignature, byte[] publicKey)
			throws GeneralSecurityException {

		// validate

		byte[] canonicalizedDocumentBytes = canonicalizedDocument.getBytes(StandardCharsets.UTF_8);
		byte[] signatureValueBytes = Base64.decodeBase64(ldSignature.getSignatureValue());
		boolean validate = EC25519Provider.get().validate(canonicalizedDocumentBytes, signatureValueBytes, publicKey);

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

	public byte[] getPublicKey() {

		return this.publicKey;
	}

	public void setPublicKey(byte[] publicKey) {

		this.publicKey = publicKey;
	}
}
