package com.idhub.magic.clientlib.test;

import com.idhub.magic.clientlib.ApiFactory;
import com.idhub.magic.clientlib.ProviderFactory;


public class ClientTest {

	public static void main(String[] args) {
		String addr = ProviderFactory.getProvider().getDefaultAddress().getAddress();
		long ein = ApiFactory.getIdentityChainViewer().getEIN(addr);
		System.out.println(ein);
	}

}
