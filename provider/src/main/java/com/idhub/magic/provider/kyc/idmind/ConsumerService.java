package com.idhub.magic.provider.kyc.idmind;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsumerService {
	 @Headers({"Content-Type: application/json", "accept: application/json"})
	@POST("/im/account/consumer")
	 Call<ConsumerKycResponse> customer(@Body ConsumerKycRequest req,@Query(value = "graphScoreResponse") boolean graphScoreResponse);
	 @GET("/im/account/consumer/v2/{mtid}")
	 Call<ConsumerKycResponse> retrievestate(@Path("mtid") String mtid);
}
//https://edoc.identitymind.com/reference#apioverview