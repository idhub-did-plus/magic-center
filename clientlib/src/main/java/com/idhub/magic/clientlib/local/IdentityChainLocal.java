package com.idhub.magic.clientlib.local;

import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import com.idhub.magic.clientlib.interfaces.Identity;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.IdentityChainViewer;

public class IdentityChainLocal implements IdentityChain, IdentityChainViewer {
	public void createIdentity(String recovery, String associate, List<String> providers, List<String> resolvers) {
		try {
			TransactionReceipt data = ContractManager.getRegistry1484().createIdentity(recovery, providers, resolvers).send();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public long getEIN(String associate) {
		BigInteger data;
		try {
			data = ContractManager.getRegistry1484().getEIN(associate).send();
			return data.longValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public Identity getIdentity(long ein) {

		try {
			 Tuple4<String, List<String>, List<String>, List<String>> data = ContractManager.getRegistry1484().getIdentity(BigInteger.valueOf(ein)).send();
			
			 if(data != null)
				return  new Identity(data.getValue1(), data.getValue2(), data.getValue3(), data.getValue4());
			 return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		

	}
}