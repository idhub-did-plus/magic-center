package com.idhub.magic.center;

import java.io.IOException;

import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.WalletUtils;

public class Utils {
	static public void main(String[] ss) throws Exception {
		String plainMessage = "hello world";
		byte[] hexMessage = Hash.sha3(plainMessage.getBytes());
		Credentials credentials = WalletUtils.loadCredentials("2218283", "D:\\magicspace\\UTC--2019-08-01T10-04-24.655000000Z--1603690b9e570ff1239880011d2eafa61954b85c.json");

		ECKeyPair pair = credentials.getEcKeyPair();
		// Use java to sign and verify the signature
		Sign.SignatureData signMessage = Sign.signMessage(hexMessage, pair);
		String pubKey = Sign.signedMessageToKey(hexMessage, signMessage).toString(16);
		String signerAddress = Keys.getAddress(pubKey);
		System.out.println("Signer address    : 0x" + signerAddress);

		// Now use java signature to verify from the blockchain
		Bytes32 message = new Bytes32(hexMessage);
		Uint8 v = new Uint8(signMessage.getV());
		Bytes32 r = new Bytes32(signMessage.getR());
		Bytes32 s = new Bytes32(signMessage.getS());

		/*
		 * String address = contract.verify(message, v, r,
		 * s).get().getValue().toString(16); String address2 =
		 * contract.verifyWithPrefix(message, v, r, s).get().getValue().toString(16);
		 * System.out.println("Recovered address1 : 0x"+address);
		 * System.out.println("Recovered address2 : 0x"+address2);
		 */
	}
	// https://ethereum.stackexchange.com/questions/17708/solidity-ecrecover-and-web3j-sign-signmessage-are-not-compatible-is-it
}
