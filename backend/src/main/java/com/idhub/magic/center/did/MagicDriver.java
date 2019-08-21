package com.idhub.magic.center.did;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.tuples.generated.Tuple4;

import com.idhub.magic.center.service.ContractManager;
import com.idhub.magic.did.Authentication;
import com.idhub.magic.did.PublicKey;
import com.idhub.magic.did.driver.Driver;
import com.idhub.magic.did.resolver.ResolutionException;
import com.idhub.magic.did.resolver.ResolveResult;
@Service
public class MagicDriver implements Driver {
	public static final String[] DIDDOCUMENT_PUBLICKEY_TYPES = new String[] { "EcdsaSecp256k1VerificationKey2019" };
	public static final String[] DIDDOCUMENT_AUTHENTICATION_TYPES = new String[] { "EcdsaSecp256k1SignatureAuthentication2019" };
	public static final Pattern DID_ERC725_PATTERN = Pattern.compile("^did:ein:(\\S+)?:(\\S+)$");
	@Autowired ContractManager contractManager;
	@Override
	public ResolveResult resolve(String identifier) throws ResolutionException {
		Matcher matcher = DID_ERC725_PATTERN.matcher(identifier);
		if (matcher.matches()){
			String eins = matcher.groupCount() == 2 ? matcher.group(2) : matcher.group(1);
			if(eins == null)
				return null;
			long ein = Long.valueOf(eins);
			return doc1484(identifier, ein);
						
		}
		return null;
	}
	

	private ResolveResult doc1484(String id, long ein) {
		try {
			Tuple4<String, List<String>, List<String>, List<String>> data = contractManager.getRegistry1484().getIdentity(BigInteger.valueOf(ein)).send();
			
			List<PublicKey> publicKeys = new ArrayList<PublicKey> ();
			List<Authentication> authentications = new ArrayList<Authentication> ();

			List<String> assos = data.getValue2();
			String rec = data.getValue1();
				for (int i = 0; i < assos.size(); i++) {
					String addr = assos.get(i);
					String keyId = id + "#key-" + i;
					PublicKey publicKey = PublicKey.build(keyId, DIDDOCUMENT_PUBLICKEY_TYPES, null, null, addr, null);
					publicKeys.add(publicKey);
				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	private String method(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, Object> properties() throws ResolutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
