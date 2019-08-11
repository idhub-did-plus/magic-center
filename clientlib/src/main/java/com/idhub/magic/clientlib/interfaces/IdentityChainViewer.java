package com.idhub.magic.clientlib.interfaces;

public interface IdentityChainViewer {
	public long getEIN(String associate);
	public Identity getIdentity(long ein);
}
