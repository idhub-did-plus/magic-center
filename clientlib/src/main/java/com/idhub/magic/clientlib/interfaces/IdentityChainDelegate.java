package com.idhub.magic.clientlib.interfaces;

import java.util.List;

import com.idhub.magic.center.contracts.IdentityRegistryInterface.IdentityCreatedEventResponse;

public interface IdentityChainDelegate {
	Listen<IdentityCreatedEventResponse> createIdentity();

	Listen<IdentityCreatedEventResponse> createIdentity(String recovery, String associate, List<String> providers, List<String> rssolvers);

}
