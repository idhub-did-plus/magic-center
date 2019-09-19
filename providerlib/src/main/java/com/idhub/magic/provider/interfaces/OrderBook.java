package com.idhub.magic.provider.interfaces;

import java.util.List;

import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;

public interface OrderBook {
	List<Order> tome(String identity);
	boolean receive(String identity, String orderId);
	List<Order> listAll();
	void issueClaim(String identity, String orderId, VerifiableCredential credential);
	void refuseClaim(String identity, String orderId);
	IdentityData getIdentityInformation( String targetIdentity);
}
