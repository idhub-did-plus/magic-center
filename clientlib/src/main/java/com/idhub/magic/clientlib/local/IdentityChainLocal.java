package com.idhub.magic.clientlib.local;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.Identity;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.IdentityChainViewer;
import com.idhub.magic.clientlib.interfaces.ResultListener;

public class IdentityChainLocal implements IdentityChain, IdentityChainViewer {
	public boolean createIdentity(String recovery, String associate, List<String> providers, List<String> resolvers, ResultListener<Boolean> listener) {
		
			
			if(listener == null) {
				try {
					TransactionReceipt data = ContractManager.getRegistry1484().createIdentity(recovery, providers, resolvers).send();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
				
			}else {
				 CompletableFuture<TransactionReceipt> data = ContractManager.getRegistry1484().createIdentity(recovery, providers, resolvers).sendAsync();
				 data.thenAccept(transactionReceipt -> {

					  	listener.result(true);

					 }).exceptionally(transactionReceipt  -> {
						 listener.result(false);
						 return null;
					 });
				 return true;
			}
			

	}
	
	public long getEIN(String associate,ResultListener<Long> listener) {
		if(listener == null) {
		try {
			BigInteger data = ContractManager.getRegistry1484().getEIN(associate).send();
			return data.longValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		}else {
			 CompletableFuture<BigInteger> data = ContractManager.getRegistry1484().getEIN(associate).sendAsync();
			 data.thenAccept(ein -> {

				  	listener.result(ein.longValue());

				 }).exceptionally(transactionReceipt  -> {
					 listener.result(0l);
					 return null;
				 });
			return 0l;
		}

	}
	
	public Identity getIdentity(long ein,ResultListener<Identity> listener) {
		if(listener == null) {
		try {
			 Tuple4<String, List<String>, List<String>, List<String>> data = ContractManager.getRegistry1484().getIdentity(BigInteger.valueOf(ein)).send();
			
			 if(data != null)
				return  new Identity(data.getValue1(), data.getValue2(), data.getValue3(), data.getValue4());
			 return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		}else {
			 CompletableFuture<Tuple4<String, List<String>, List<String>, List<String>>> data = ContractManager.getRegistry1484().getIdentity(BigInteger.valueOf(ein)).sendAsync();
			 data.thenAccept(id -> {
				 Identity identity = new Identity(id.getValue1(), id.getValue2(), id.getValue3(), id.getValue4());
				  	listener.result(identity);

				 }).exceptionally(transactionReceipt  -> {
					 listener.result(null);
					 return null;
				 });
			return null;
		}
		

	}

	@Override
	public boolean createIdentity(ResultListener<Boolean> listener) {
		String rec = ProviderFactory.getProvider().getRecoverCredentials().getAddress();
		String asso = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		List<String> ps = new ArrayList<String>();
		List<String> rs = new ArrayList<String>();
		rs.add(DeployedContractAddress.IdentityRegistryInterface);
		
		
		boolean rst = createIdentity(rec, asso, ps, rs, listener);
		return rst;
		
	}
}