package com.idhub.magic.provider.agent;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class AccountManager {
	 private static final Logger log = LoggerFactory.getLogger(AccountManager.class);

	static Credentials myAccount;
	static {

			//File source = new File("keystore\\keys.json");
			//credentials = WalletUtils.loadCredentials("2218283", source);
			myAccount = Credentials.create("0f2e67d493a271e2421929cb56f58bce05b27c081f18ab1b9491b4394e0116a2");
			log.info("Credentials loaded");
		
	}
	public static Credentials getMyAccount() {
		return myAccount;
	}

}