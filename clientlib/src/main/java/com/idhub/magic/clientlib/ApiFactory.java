package com.idhub.magic.clientlib;

import com.idhub.magic.clientlib.delegatiton.IdentityChainDelegateImpl;
import com.idhub.magic.clientlib.event.EventFetcher;
import com.idhub.magic.clientlib.http.RetrofitAccessor;
import com.idhub.magic.clientlib.interfaces.EventListenerService;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.IdentityChainDelegate;
import com.idhub.magic.clientlib.interfaces.IdentityChainViewer;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;
import com.idhub.magic.clientlib.interfaces.KycService;
import com.idhub.magic.clientlib.local.IdentityChainLocal;

public class ApiFactory {
	static IdentityChainLocal local = new IdentityChainLocal();
	static IdentityChainDelegate delegation = new IdentityChainDelegateImpl();

	static RetrofitAccessor retrofitAccessor = RetrofitAccessor.getInstance();
	
	public static IdentityStorage getArchiveStorage() {
		return retrofitAccessor.getIdentityStorage();
	}
	static public IdentityChainDelegate getIdentityChainDelegate() {
		return delegation;
	}
	static public EventListenerService getEventListenerService() {
		return EventFetcher.getInstance();
	}
	
	static public KycService getKycService() {
		return retrofitAccessor.getKycService();
	}
	//上面是跟后端访问接口
	//后面不访问后端，仅供参考
	static public IdentityChain getIdentityChainLocal() {
		return local;
	}
	static public IdentityChainViewer getIdentityChainViewer() {
		return local;
	}

	
}
