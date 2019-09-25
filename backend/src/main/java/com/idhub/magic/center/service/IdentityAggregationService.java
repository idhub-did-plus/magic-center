package com.idhub.magic.center.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple4;

@Service
public class IdentityAggregationService {
	Map<String, List<String>> cache = new HashMap<String, List<String>>();
	@Autowired ContractManager manager;
	public long ein(String address) {
		BigInteger ein;
		try {
			ein = manager.registry1484.getEIN(address).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if(ein.longValue() <= 0)
			throw new RuntimeException("there is no aggregation for this address!");
		return ein.longValue();
	}
	public boolean validate(String addr1, String addr2) {
		try {
			boolean same =  addr1.equals(addr2);
			if(same)
				return true;
			BigInteger ein = manager.registry1484.getEIN(addr1).send();
			if(ein.longValue() <= 0)
				return false;
			
			Tuple4<String, List<String>, List<String>, List<String>> identity = manager.registry1484.getIdentity(ein).send();
			
			
			return identity.getValue2().contains(addr2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		
	}
	public MyIden associates(String identity) {
		try {
			
			BigInteger ein = manager.registry1484.getEIN(identity).send();
			if(ein.longValue() <= 0)
				return null;
			
			Tuple4<String, List<String>, List<String>, List<String>> id = manager.registry1484.getIdentity(ein).send();
			
			
			return new MyIden(id.getValue2(), ein.longValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		
	}
	

}
