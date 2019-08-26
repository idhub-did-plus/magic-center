package info.weboftrust.ldsignatures;

import com.idhub.magic.ldsignatures.signer.EcdsaKoblitzSignature2016LdSigner;
import com.idhub.magic.ldsignatures.signer.Ed25519Signature2018LdSigner;
import com.idhub.magic.ldsignatures.signer.LdSigner;
import com.idhub.magic.ldsignatures.signer.RsaSignature2018LdSigner;

import junit.framework.TestCase;

public class LdSignersTest extends TestCase {

	public void testLdSigners() throws Exception {

		assertEquals(LdSigner.ldSignerForSignatureSuite("Ed25519Signature2018").getClass(), Ed25519Signature2018LdSigner.class);
		assertEquals(LdSigner.ldSignerForSignatureSuite("EcdsaKoblitzSignature2016").getClass(), EcdsaKoblitzSignature2016LdSigner.class);
		assertEquals(LdSigner.ldSignerForSignatureSuite("RsaSignature2018").getClass(), RsaSignature2018LdSigner.class);
	}
}
