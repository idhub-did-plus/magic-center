package com.idhub.magic.provider.service;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
//import org.web3j.model.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
//import org.web3j.tx.gas.ContractGasProvider;
//import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import com.idhub.magic.common.contracts.ERC1056ResolverInterface;
import com.idhub.magic.common.contracts.EthereumClaimsRegistryInterface;
import com.idhub.magic.common.contracts.EthereumDIDRegistryInterface;
import com.idhub.magic.common.contracts.IdentityRegistryInterface;
import com.idhub.magic.common.service.DeployedContractAddress;
import com.idhub.magic.provider.agent.AccountManager;

@Service
public class ContractManager {

	private static final Logger log = LoggerFactory.getLogger(ContractManager.class);

	@Autowired
	private Web3j web3j;

	IdentityRegistryInterface registry1484;
	ERC1056ResolverInterface resolver1056;
	EthereumDIDRegistryInterface registry1056;
	EthereumClaimsRegistryInterface registry780;
	Credentials credentials;

	@PostConstruct
	private void init() throws Exception, CipherException {
		credentials = AccountManager.getMyAccount();
		
		ContractGasProvider contractGasProvider = new DefaultGasProvider();

        registry1484 = IdentityRegistryInterface.load(DeployedContractAddress.getInstance().addr1484(),
                web3j,
                credentials,
                contractGasProvider
        );
        resolver1056 = ERC1056ResolverInterface.load(DeployedContractAddress.getInstance().addr1056Resolver(),
                web3j,
                credentials,
                contractGasProvider
        );
        registry1056 = EthereumDIDRegistryInterface.load(DeployedContractAddress.getInstance().addr1056(),
                web3j,
                credentials,
                contractGasProvider
        );
        registry780 = registry780.load(DeployedContractAddress.getInstance().addr780(),
                web3j,
                credentials,
                contractGasProvider
        );

	}

	public EthereumClaimsRegistryInterface getRegistry780() {
		return registry780;
	}

	public ERC1056ResolverInterface getResolver1056() {
		return resolver1056;
	}

	public IdentityRegistryInterface getRegistry1484() {
		return registry1484;
	}

	void send() throws Exception {

		// FIXME: Request some Ether for the Rinkeby test network at
		// https://www.rinkeby.io/#faucet
		log.info("Sending 1 Wei (" + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
		TransactionReceipt transferReceipt = Transfer
				.sendFunds(web3j, credentials, "0x19e03255f667bdfd50a32722df860b1eeaf4d635", // you can put any address
																								// here
						BigDecimal.ONE, Convert.Unit.WEI) // 1 wei = 10^-18 Ether
				.send();
		log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
				+ transferReceipt.getTransactionHash());

	}
}