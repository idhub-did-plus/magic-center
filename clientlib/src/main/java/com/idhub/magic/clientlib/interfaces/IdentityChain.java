package com.idhub.magic.clientlib.interfaces;

import java.util.List;

import com.idhub.magic.center.contracts.IdentityRegistryInterface.IdentityCreatedEventResponse;

public interface IdentityChain extends IdentityChainDelegate{
	public Listen<Long> getEIN(String associate);
	public Listen<Identity> getIdentity(long ein);
}
