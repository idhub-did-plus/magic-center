package info.weboftrust.ldsignatures;

import java.net.URI;
import java.util.LinkedHashMap;

import com.github.jsonldjava.utils.JsonUtils;
import com.idhub.magic.ldsignatures.LdSignature;
import com.idhub.magic.ldsignatures.signer.Ed25519Signature2018LdSigner;
import com.idhub.magic.ldsignatures.suites.SignatureSuites;
import com.idhub.magic.ldsignatures.validator.Ed25519Signature2018LdValidator;

import junit.framework.TestCase;

public class JsonLdSignEd25519Signature2018Test extends TestCase {

	@SuppressWarnings("unchecked")
	public void testSignEd25519Signature2018() throws Exception {

		LinkedHashMap<String, Object> jsonLdObject = (LinkedHashMap<String, Object>) JsonUtils.fromInputStream(JsonLdSignEd25519Signature2018Test.class.getResourceAsStream("input.jsonld"));

		URI creator = URI.create("did:sov:WRfXPg8dantKVubE3HX8pw");
		String created = "2017-10-24T05:33:31Z";
		String domain = "example.com";
		String nonce = null;

		Ed25519Signature2018LdSigner signer = new Ed25519Signature2018LdSigner(creator, created, domain, nonce, TestUtil.testEd25519PrivateKey);
		LdSignature ldSignature = signer.sign(jsonLdObject);

		assertEquals(SignatureSuites.SIGNATURE_SUITE_ED25519SIGNATURE2018.getTerm(), ldSignature.getType());
		assertEquals(creator, ldSignature.getCreator());
		assertEquals(created, ldSignature.getCreated());
		assertEquals(domain, ldSignature.getDomain());
		assertEquals(nonce, ldSignature.getNonce());
		assertEquals("if8ooA+32YZc4SQBvIDDY9tgTatPoq4IZ8Kr+We1t38LR2RuURmaVu9D4shbi4VvND87PUqq5/0vsNFEGIIEDA==", ldSignature.getSignatureValue());

		Ed25519Signature2018LdValidator validator = new Ed25519Signature2018LdValidator(TestUtil.testEd25519PublicKey);
		boolean validate = validator.validate(jsonLdObject, ldSignature);
		assertTrue(validate);
	}
}
