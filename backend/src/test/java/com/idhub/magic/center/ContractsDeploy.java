package com.idhub.magic.center;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import com.idhub.magic.center.account.AccountManager;

public class ContractsDeploy {

	public static void main(String[] args) {
		 Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
		 ContractGasProvider contractGasProvider = new DefaultGasProvider();
		/*
		 * try { EthereumClaimsRegistry contract = EthereumClaimsRegistry.deploy( web3j,
		 * AccountManager.getServer(), contractGasProvider ).send();
		 * System.out.println(contract.getContractAddress()); } catch (Exception e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
	}

}
