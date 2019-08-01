package com.idhub.magic.center.util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

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
		ECDSASignature sig = new ECDSASignature(new BigInteger(signMessage.getR()),new BigInteger(signMessage.getS()));
		BigInteger pk = Sign.recoverFromSignature(0, sig, hexMessage);
		/*
		 * String address = contract.verify(message, v, r,
		 * s).get().getValue().toString(16); String address2 =
		 * contract.verifyWithPrefix(message, v, r, s).get().getValue().toString(16);
		 * System.out.println("Recovered address1 : 0x"+address);
		 * System.out.println("Recovered address2 : 0x"+address2);
		 */
	}

	/*
	 * void test() {
	 * 
	 * try { String privateKey_ = "123456"; String contractAddress_ = "111111";
	 * 
	 * Credentials credentials = Credentials.create(privateKey_); String fromAddress
	 * = credentials.getAddress(); Web3j web3j = Web3j.build(new HttpService());
	 * EthGetTransactionCount ethGetTransactionCount = web3j
	 * .ethGetTransactionCount(fromAddress,
	 * DefaultBlockParameterName.LATEST).sendAsync().get(); BigInteger nonce =
	 * ethGetTransactionCount.getTransactionCount();
	 * 
	 * List<Type> inputParameters = new ArrayList<>();
	 * 
	 * Address[] address = {new Address("0x092754..."),new
	 * Address("0x2b6186.....")}; Uint256[] theBalance = {new Uint256(10),new
	 * Uint256(20)};
	 * 
	 * //这里加入会报错 inputParameters.add(Arrays.asList(address));
	 * inputParameters.add(Arrays.asList(theBalance));
	 * 
	 * List<TypeReference<?>> outputParameters = new ArrayList<>();
	 * TypeReference<Bool> result = new TypeReference<Bool>() {};
	 * outputParameters.add(result);
	 * 
	 * Function function = new Function("setMultiPlyRewardInfo",
	 * inputParameters,outputParameters); String encodedFunction =
	 * FunctionEncoder.encode(function);
	 * 
	 * RawTransaction rawTransaction = RawTransaction.createTransaction(nonce,
	 * Convert.toWei("1", Convert.Unit.GWEI).toBigInteger(), new
	 * BigInteger("5000000"), contractAddress_, encodedFunction);
	 * 
	 * byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction,(byte)
	 * 18928,credentials); String hexValue = Numeric.toHexString(signedMessage);
	 * 
	 * EthSendTransaction ethSendTransaction =
	 * web3j.ethSendRawTransaction(hexValue).sendAsync().get(); return
	 * ethSendTransaction.getTransactionHash(); } catch (InterruptedException e) {
	 * e.printStackTrace(); return ""; } catch (ExecutionException e) {
	 * e.printStackTrace(); return ""; } }
	 */
	public static boolean authenticate(String signed, String original) {
		ECDSASignature
		return null;
	}


}
