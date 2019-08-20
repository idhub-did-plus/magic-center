package info.weboftrust.ldsignatures;

import com.idhub.magic.ldsignatures.validator.EcdsaKoblitzSignature2016LdValidator;
import com.idhub.magic.ldsignatures.validator.Ed25519Signature2018LdValidator;
import com.idhub.magic.ldsignatures.validator.LdValidator;
import com.idhub.magic.ldsignatures.validator.RsaSignature2018LdValidator;

import junit.framework.TestCase;

public class LdValidatorsTest extends TestCase {

	public void testLdValidators() throws Exception {

		assertEquals(LdValidator.ldValidatorForSignatureSuite("Ed25519Signature2018").getClass(), Ed25519Signature2018LdValidator.class);
		assertEquals(LdValidator.ldValidatorForSignatureSuite("EcdsaKoblitzSignature2016").getClass(), EcdsaKoblitzSignature2016LdValidator.class);
		assertEquals(LdValidator.ldValidatorForSignatureSuite("RsaSignature2018").getClass(), RsaSignature2018LdValidator.class);
	}
}
