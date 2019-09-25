package com.idhub.magic.provider.agent;

import java.util.List;

import com.idhub.magic.common.parameter.MagicResponse;
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
	public  Call<MagicResponse<List<Order>>> directed(@Query("identity") String providerIdentity) ;
	@GET("orderbook/receive")
	public Call<MagicResponse> receive(@Query("identity") String providerIdentity, @Query("orderId") String orderId);
	@GET("orderbook/list_all")
	public Call<MagicResponse<List<Order>>> listAll(@Query("identity") String providerIdentity);
	@POST("orderbook/issue_claim")
	public Call<MagicResponse> issueClaim(@Query("identity") String providerIdentity, @Query("orderId") String orderId,@Body String credential) ;
	@POST("orderbook/refuse_claim")
	public Call<MagicResponse> refuseClaim(@Query("identity") String providerIdentity, @Query("orderId") String orderId) ;

	@GET("orderbook/get_identity_information")
	public Call<MagicResponse<IdentityData>> getIdentityInformation(@Query("identity") String providerIdentity, @Query("orderId") String orderId);
}
