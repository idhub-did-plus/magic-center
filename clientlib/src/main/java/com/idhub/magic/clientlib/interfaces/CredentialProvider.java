package com.idhub.magic.clientlib.interfaces;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

public interface CredentialProvider {
	Credentials getByAddress(String address);
	Credentials getDefaultAddress();
	Web3j web3j();
}
