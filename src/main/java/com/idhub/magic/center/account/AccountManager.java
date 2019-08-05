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
	static Credentials temp;
	static {
		try {
			
			credentials = WalletUtils.loadCredentials("2218283", "D:\\magicspace\\magic-center\\keystore\\keys.json");
			temp = Credentials.create("fe993e597ecc6412936be860748c4ca266d14931ee0bbd65a0f8d08c72d183f8");
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
		return temp;
	}
	
}
