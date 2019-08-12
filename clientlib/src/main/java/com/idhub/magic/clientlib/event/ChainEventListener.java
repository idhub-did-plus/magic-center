package com.idhub.magic.clientlib.event;


import java.util.Arrays;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;

import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.clientlib.ProviderFactory;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class ChainEventListener {
	public void init() {
	
	    final Event event = new Event("Approval",
	            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
	    EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,DeployedContractAddress.IdentityRegistryInterface.substring(2));
	    filter.addSingleTopic(EventEncoder.encode(event));
	   
	    Disposable dis = ProviderFactory.getProvider().web3j().ethLogFlowable(filter).filter(log ->{
	    	String data = log.getData();
	    	return false;
	    	}).map(new Function<Log, CreateEntityEvent>() {
			@Override
			public CreateEntityEvent apply(Log t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
	    }).subscribe();
	}
}
