package com.idhub.magic.clientlib;

import com.idhub.magic.clientlib.interfaces.CredentialProvider;

public class ProviderFactory {
	static CredentialProvider provider;
	static {
		try {
			provider = (CredentialProvider) Class.forName("com.idhub.magic.clientlib.test.MockProvider").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public CredentialProvider getProvider() {
		return provider;
	}

}