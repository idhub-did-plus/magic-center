package com.idhub.magic.clientlib.interfaces;

import java.util.List;

public interface IdentityChain {
	void createIdentity(String recovery, String associate, List<String> providers, List<String> rssolvers);
}
