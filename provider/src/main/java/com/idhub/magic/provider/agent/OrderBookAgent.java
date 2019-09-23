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
	public Call<Boolean> receive(@Query("identity") String providerIdentity, @Query("orderId") String orderId);
	@GET("orderbook/list_all")
	public Call<List<Order>> listAll(@Query("identity") String providerIdentity);
	@POST("orderbook/issue_claim")
	public Call<Void> issueClaim(@Query("identity") String providerIdentity, @Query("orderId") String orderId,@Body VerifiableCredential credential) ;
	@POST("orderbook/refuse_claim")
	public Call<Void> refuseClaim(@Query("identity") String providerIdentity, @Query("orderId") String orderId) ;

	@GET("orderbook/get_identity_information")
	public Call<IdentityData> getIdentityInformation(@Query("identity") String providerIdentity, @Query("targetIdentity") String targetIdentity);
}
