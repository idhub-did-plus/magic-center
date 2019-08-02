package com.idhub.magic.center.crypto;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class CryptoManager {
	 private static final Logger log = LoggerFactory.getLogger(CryptoManager.class);
	static Credentials credentials;
	static {
		try {
			credentials = WalletUtils.loadCredentials("2218283", "D:\\magicspace\\UTC--2019-08-01T10-04-24.655000000Z--1603690b9e570ff1239880011d2eafa61954b85c.json");

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
