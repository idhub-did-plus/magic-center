package com.idhub.magic.clientlib.interfaces;

public interface IdentityChainViewer {
	public long getEIN(String associate,ResultListener<Long> listener);
	public Identity getIdentity(long ein,ResultListener<Identity> listener);
}
