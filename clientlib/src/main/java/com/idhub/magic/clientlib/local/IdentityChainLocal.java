package com.idhub.magic.clientlib.local;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import com.idhub.magic.center.contracts.IdentityRegistryInterface.IdentityCreatedEventResponse;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.ExceptionListener;
import com.idhub.magic.clientlib.interfaces.Identity;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.IdentityChainViewer;
import com.idhub.magic.clientlib.interfaces.Listen;
import com.idhub.magic.clientlib.interfaces.ResultListener;

public class IdentityChainLocal implements IdentityChain, IdentityChainViewer {

	public Listen<Long> getEIN(String associate) {

		CompletableFuture<BigInteger> data = ContractManager.getRegistry1484().getEIN(associate).sendAsync();
		return new Listen<Long>() {

			@Override
			public void listen(ResultListener<Long> l, ExceptionListener el) {
				data.thenAccept(ein -> {
					l.result(ein.longValue());
				}).exceptionally(transactionReceipt -> {
					el.error("error!");
					
					return null;
				});

			}
		};

	}

	public Listen<Identity> getIdentity(long ein) {

		CompletableFuture<Tuple4<String, List<String>, List<String>, List<String>>> data = ContractManager
				.getRegistry1484().getIdentity(BigInteger.valueOf(ein)).sendAsync();
		return new Listen<Identity>() {

			@Override
			public void listen(ResultListener<Identity> l, ExceptionListener el) {
				data.thenAccept(id -> {
					Identity identity = new Identity(id.getValue1(), id.getValue2(), id.getValue3(), id.getValue4());
					l.result(identity);

				}).exceptionally(transactionReceipt -> {
					l.result(null);
					return null;
				});

			}
		};

	}

	@Override
	public Listen<IdentityCreatedEventResponse> createIdentity() {
		String rec = ProviderFactory.getProvider().getRecoverCredentials().getAddress();
		String asso = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		List<String> ps = new ArrayList<String>();
		List<String> rs = new ArrayList<String>();
		rs.add(DeployedContractAddress.IdentityRegistryInterface);

		return createIdentity(rec, asso, ps, rs);

	}

	@Override
	public Listen<IdentityCreatedEventResponse> createIdentity(String recovery, String associate,
			List<String> providers, List<String> resolvers) {
		CompletableFuture<TransactionReceipt> data = ContractManager.getRegistry1484()
				.createIdentity(recovery, providers, resolvers).sendAsync();
		return new Listen<IdentityCreatedEventResponse>() {

			@Override
			public void listen(ResultListener listener, ExceptionListener el) {
				data.thenAccept(transactionReceipt -> {
					List<IdentityCreatedEventResponse> es = ContractManager.getRegistry1484()
							.getIdentityCreatedEvents(transactionReceipt);
					listener.result(es.get(0));

				}).exceptionally(transactionReceipt -> {
					listener.result(null);
					return null;
				});

			}

		};

	}
}