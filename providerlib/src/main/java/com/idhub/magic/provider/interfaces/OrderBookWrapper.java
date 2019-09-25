package com.idhub.magic.provider.interfaces;

import java.util.List;

import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;

public interface OrderBookWrapper {
	MagicResponse<List<Order>> tome(String identity);
	MagicResponse receive(String identity, String orderId);
	MagicResponse<List<Order>> listAll();
	MagicResponse issueClaim(String identity, String orderId, String credential);
	MagicResponse refuseClaim(String identity, String orderId);
	MagicResponse<IdentityData> getIdentityInformation(String identity, String orderId);
}
