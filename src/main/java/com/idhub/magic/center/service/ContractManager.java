package com.idhub.magic.center.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
//import org.web3j.model.Greeter;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
//import org.web3j.tx.gas.ContractGasProvider;
//import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.idhub.magic.contracts.DeployedContractAddress;
import com.idhub.magic.contracts.IdentityRegistry;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ContractManager {

    private static final Logger log = LoggerFactory.getLogger(ContractManager.class);
    
	@Autowired
    private Web3j web3j;

	 IdentityRegistry registry1484;
	 
	 Credentials credentials;
    private void init() throws Exception, CipherException {
    	credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "/path/to/<walletfile>");
        log.info("Credentials loaded");
        
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
    	IdentityRegistry contract = IdentityRegistry.load(DeployedContractAddress.IdentityRegistry,
                web3j,
                credentials,
                contractGasProvider
        );
    }
    
     public IdentityRegistry getRegistry1484() {
		return registry1484;
	}

	void send() throws Exception{
       

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        log.info("Sending 1 Wei ("
                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());

      
    }
}