package com.idhub.magic.provider.agent;

import java.util.List;

import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderBookAgent{
	@GET("orderbook/directed")
	public  Call<List<Order>> directed(@Query("identity") String providerIdentity) ;
	@GET("orderbook/receive")
	public Call<Boolean> receive(@Query("identity") String providerIdentity, String orderId);
	@GET("orderbook/list-all")
	public Call<List<Order>> listAll(@Query("identity") String providerIdentity);
	@POST("orderbook/issue-claim")
	public Call<Void> issueClaim(@Query("identity") String providerIdentity, @Body VerifiableCredential credential) ;
	@GET("orderbook/get-identity-information")
	public Call<IdentityData> getIdentityInformation(@Query("identity") String providerIdentity, String targetIdentity);
}
