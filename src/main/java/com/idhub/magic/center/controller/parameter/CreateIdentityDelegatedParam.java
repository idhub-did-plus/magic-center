package com.idhub.magic.center.controller.parameter;
// public RemoteCall<TransactionReceipt> createIdentityDelegated(String recoveryAddress, String associatedAddress, List<String> providers, List<String> resolvers, BigInteger v, byte[] r, byte[] s, BigInteger timestamp) {
import java.util.List;

public class CreateIdentityDelegatedParam {
	public String recoveryAddress;//address的 的hexcode,可以带0x
	public String associatedAddress;
	public List<String> providers;
	public List<String> resolvers;
	public int v;//27\28->BigInteger
	public String  r;//base64
	public String s;//base64
	public String timestamp;//timestamp->base64.decode->BigInteger(byte[]);
}
