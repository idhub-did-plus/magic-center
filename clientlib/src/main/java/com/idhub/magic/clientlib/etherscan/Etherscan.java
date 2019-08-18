package com.idhub.magic.clientlib.etherscan;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.http.HttpAccessor;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Tx;

public class Etherscan {
	static Etherscan instance;
	static {
		instance = new Etherscan();
		instance.init();
	}
	public static Etherscan getInstance() {
		return instance;
	}
	static public void main(String[] ss) throws Exception {
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, new TrustManager[] { new TrustAllX509TrustManager() }, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
		    public boolean verify(String string,SSLSession ssls) {
		        return true;
		    }
		});
		   Etherscan scan = Etherscan.getInstance();
		   List<String> accs = new ArrayList<String>();
		   accs.add("0xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a");
		   scan.setAccounts(accs);
	   }
	
	
	
    ScheduledExecutorService pool;
    List<String> accounts = new ArrayList<String>();
	List<String> tokenContractAddresses = new ArrayList<String>();

	EtherScanApi api = new EtherScanApi();  
    IncomingListener<Tx> txlistener;
    IncomingListener<Transfer> transferlistener;
    void init() {
		pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(() -> {
			try {
				once();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}, 0, 15, TimeUnit.SECONDS);
	}

    void once() throws Exception {
    	long start = 2l;//ProviderFactory.getProvider().getLastEndBlockNumber();
    	long end =99999999l;// ProviderFactory.getProvider().web3j().ethBlockNumber().send().getBlockNumber().longValue();
    	TransactionSession sess = new TransactionSession( accounts,txlistener,tokenContractAddresses,transferlistener, api,  start, end);
    	sess.execute();
    }
    public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}
    public void setTokenContractAddresses(List<String> tokenContractAddresses) {
		this.tokenContractAddresses = tokenContractAddresses;
	}
   void subscribeTransaction(IncomingListener<Tx> txl) {
	txlistener = txl;
}
void subscribeTransfer(IncomingListener<Transfer> txl) {
	transferlistener = txl;
}

}
 class TrustAllX509TrustManager implements X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

    public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

}