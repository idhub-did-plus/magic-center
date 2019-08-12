package com.idhub.magic.clientlib.delegatiton;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.event.Event;
import com.idhub.magic.center.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DeployedContractAddress;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.event.EventFetcher;
import com.idhub.magic.clientlib.event.EventListener;
import com.idhub.magic.clientlib.http.HttpAccessor;
import com.idhub.magic.clientlib.interfaces.IdentityChain;
import com.idhub.magic.clientlib.interfaces.ResultListener;

public class IdentityChainDelegate implements IdentityChain{

	public boolean createIdentity(String recovery, String associate, List<String> providers, List<String> resolvers, ResultListener<Boolean> listener) {

		CreateIdentityDelegatedParam rst = new CreateIdentityDelegatedParam();
		rst.associatedAddress = associate;
		rst.recoveryAddress = recovery;
		rst.providers = providers;
		rst.resolvers = resolvers;

		rst.timestamp = Numeric.toHexString(BigInteger.valueOf(System.currentTimeMillis() / 1000).toByteArray());
		rst = ClientEncoder.encode(rst);
		String url = "http://localhost:8080/delegation/createIdentity";
		rst.async = listener != null? true:false;
		String body = JSON.toJSONString(rst);
		HttpAccessor.post(associate, url, body);
		if(listener != null) {
			EventFetcher.listen(new EventListener() {

				@Override
				public boolean filt(Event e) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void onEvent(Event e) {
					listener.result(true);
					
				}});
		}
		return true;

	}

	static CreateIdentityDelegatedParam param(String recovery, String associate, List<String> providers, List<String> resolvers) {
		CreateIdentityDelegatedParam rst = new CreateIdentityDelegatedParam();
		rst.associatedAddress = associate;
		rst.recoveryAddress = recovery;
		rst.providers = providers;
		rst.resolvers = resolvers;

		rst.timestamp = Numeric.toHexString(BigInteger.valueOf(System.currentTimeMillis() / 1000).toByteArray());
		rst = ClientEncoder.encode(rst);
		return rst;

	}

	@Override
	public boolean createIdentity(ResultListener<Boolean> listener) {
		String rec = ProviderFactory.getProvider().getRecoverCredentials().getAddress();
		String asso = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		List<String> ps = new ArrayList<String>();
		List<String> rs = new ArrayList<String>();
		rs.add(DeployedContractAddress.IdentityRegistryInterface);
		
		return createIdentity(rec, asso, ps, rs,listener);
	}

}
