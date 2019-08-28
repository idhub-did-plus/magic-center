package com.idhub.magic.center.kyc.idmind;

import com.idhub.magic.center.kyc.idmind.entity.customer.CustomerKycRequest;
import com.idhub.magic.center.kyc.idmind.entity.customer.CustomerKycResponse;

import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerService {
	 @Headers({"Content-Type: application/json", "accept: application/json"})
	@POST
	CustomerKycResponse customer(@Body CustomerKycRequest req,@Query(value = "graphScoreResponse") boolean graphScoreResponse);
}
//https://edoc.identitymind.com/reference#apioverview