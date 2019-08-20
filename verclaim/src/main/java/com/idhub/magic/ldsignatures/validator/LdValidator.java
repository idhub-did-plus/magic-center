package com.idhub.magic.ldsignatures.validator;

import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;

import com.github.jsonldjava.core.JsonLdError;
import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.suites.SignatureSuite;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;
import com.idhub.magic.ldsignatures.util.CanonicalizationUtil;

public abstract class LdValidator<SIGNATURESUITE extends SignatureSuite> {

	protected SIGNATURESUITE signatureSuite;

	protected LdValidator(SIGNATURESUITE signatureSuite) {

		this.signatureSuite = signatureSuite;
	}

	public static LdValidator<? extends SignatureSuite> ldValidatorForSignatureSuite(String signatureSuite) {

		if (SignatureSuites.SIGNATURE_SUITE_RSASIGNATURE2018.getTerm().equals(signatureSuite))
			return new RsaSignature2018LdValidator();
		if (SignatureSuites.SIGNATURE_SUITE_ED25519SIGNATURE2018.getTerm().equals(signatureSuite))
			return new Ed25519Signature2018LdValidator();
		if (SignatureSuites.SIGNATURE_SUITE_ECDSAKOBLITZSIGNATURE2016.getTerm().equals(signatureSuite))
			return new EcdsaKoblitzSignature2016LdValidator();

		throw new IllegalArgumentException();
	}

	public static LdValidator<? extends SignatureSuite> ldValidatorForSignatureSuite(SignatureSuite signatureSuite) {

		return ldValidatorForSignatureSuite(signatureSuite.getTerm());
	}

	public abstract boolean validate(String canonicalizedDocument, LdSignature ldSignature)
			throws GeneralSecurityException;

	public boolean validate(LinkedHashMap<String, Object> jsonLdObject, LdSignature ldSignature)
			throws JsonLdError, GeneralSecurityException {

		// obtain the canonicalized document

		LinkedHashMap<String, Object> jsonLdObjectWithoutSignature = new LinkedHashMap<String, Object>(jsonLdObject);
		LdSignature.removeFromJsonLdObject(jsonLdObjectWithoutSignature);
		String canonicalizedDocument = CanonicalizationUtil.buildCanonicalizedDocument(jsonLdObjectWithoutSignature);

		// validate

		boolean validate = this.validate(canonicalizedDocument, ldSignature);

		// done

		return validate;
	}

	public boolean validate(LinkedHashMap<String, Object> jsonLdObject) throws JsonLdError, GeneralSecurityException {

		// obtain the signature object

		LdSignature ldSignature = LdSignature.getFromJsonLdObject(jsonLdObject);
		if (ldSignature == null)
			return false;

		// done

		return this.validate(jsonLdObject, ldSignature);
	}
}
