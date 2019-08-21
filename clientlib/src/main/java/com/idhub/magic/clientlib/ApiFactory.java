package com.idhub.magic.clientlib;

import com.idhub.magic.clientlib.delegatiton.IdentityChainDelegateImpl;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.IdentityChainDelegate;
import com.idhub.magic.clientlib.interfaces.IdentityChainViewer;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;
import com.idhub.magic.clientlib.local.IdentityChainLocal;

public class ApiFactory {
	static IdentityChainLocal local = new IdentityChainLocal();
	static IdentityChainDelegate delegation = new IdentityChainDelegateImpl();
	static IdentityStorage archiveStorage;
	public static IdentityStorage getArchiveStorage() {
		return archiveStorage;
	}
	static public IdentityChain getIdentityChainLocal() {
		return local;
	}
	static public IdentityChainViewer getIdentityChainViewer() {
		return local;
	}
	static public IdentityChainDelegate getIdentityChainDelegate() {
		return delegation;
	}
}
