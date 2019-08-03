package com.idhub.magic.center.account;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class AccountManager {
	 private static final Logger log = LoggerFactory.getLogger(AccountManager.class);
	static Credentials credentials;
	static {
		try {
			
			credentials = WalletUtils.loadCredentials("2218283", "D:\\magicspace\\magic-center\\keystore\\keys.json");

			log.info("Credentials loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public Credentials getCredentials() {
		return credentials;
	}

}
