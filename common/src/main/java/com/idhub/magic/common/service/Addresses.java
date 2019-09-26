package com.idhub.magic.common.service;
public class Addresses{
	static public  final int IdentityRegistryInterface = 0;
	static public  final int ERC1056ResolverInterface = 1;
	static public  final int EthereumDIDRegistryInterface = 2;
	static public  final int EthereumClaimsRegistryInterface = 3;
	String[] addresses;
	 public String addr1484() {
		return addresses[IdentityRegistryInterface];
	}
	 public Addresses(String[] addresses) {
		super();
		this.addresses = addresses;
	}
	public String addr1056() {
		return addresses[EthereumDIDRegistryInterface];
	}
	 public String addr1056Resolver() {
		return addresses[ERC1056ResolverInterface];
	}
	 public String addr780() {
		return addresses[EthereumClaimsRegistryInterface];
	}
}