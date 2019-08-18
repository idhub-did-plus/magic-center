package com.idhub.magic.clientlib.etherscan;

import java.util.ArrayList;
import java.util.List;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Tx;

public class TransactionSession {
	 List<String> accounts = new ArrayList<String>();
	    List<String> tokenContractAddresses = new ArrayList<String>();  
	    EtherScanApi api = new EtherScanApi();
	    IncomingListener<Tx> txlistener;
	    IncomingListener<Transfer> transferlistener;
	    long start;
	    long end;
	    
	    
	    void once() {
	    	 
	    }
	    
	      public TransactionSession(List<String> accounts,IncomingListener<Tx> txlistener, List<String> tokenContractAddresses, IncomingListener<Transfer> transferlistener, EtherScanApi api,  long start,
				long end) {
			super();
			this.accounts = accounts;
			this.tokenContractAddresses = tokenContractAddresses;
			this.api = api;
			this.start = start;
			this.end = end;
		}
	    void execute() {
	    	List<Tx> incoming = new ArrayList<Tx>();
	    	for(String addr : accounts) {
	    		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		List<Tx> in = incomingTxs(addr);
	    		incoming.addAll(in);
	    		
	    	}
	    	System.out.println(incoming);
	    	if(txlistener != null)
	    		this.txlistener.income(incoming);
	    }
		List<Tx> incomingTxs(String address){
	    	 
	    	   List<Tx> txs = api.account().txs(address,start, end);
	    	   List<Tx> rst = new ArrayList<Tx>();
	    	  
	    	   for(Tx t : txs) {
	    		 
	    		  if( t.getTo().equals(address)){
	    			   rst.add(t);
	    		   }
	    	   }
	    	   return rst;
	      }
		
}
