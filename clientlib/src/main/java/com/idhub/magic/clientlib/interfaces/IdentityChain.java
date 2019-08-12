package com.idhub.magic.clientlib.interfaces;

import java.util.List;

public interface IdentityChain {
	boolean createIdentity(ResultListener<Boolean> listener);

	boolean createIdentity(String recovery, String associate, List<String> providers, List<String> rssolvers,ResultListener<Boolean> listener);
}
