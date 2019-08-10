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
	static Credentials server;
	static Credentials client;
	static {
		try {
			
			credentials = WalletUtils.loadCredentials("2218283", "C:\\mspace\\magic-center\\keystore\\keys.json");
			server = Credentials.create("ac913fcc48c3524e995a5b1a30b8ab8dbee69091fd72e65b1b0afcc4575eb9c4");
			client = Credentials.create("8c4020481e63b3213dfc1562e5ba61d3558877c5a93969353a426b27c51541fe");
			log.info("Credentials loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public Credentials getServer() {
		return server;
	}
	static public Credentials getClient() {
		return client;
	}
}
