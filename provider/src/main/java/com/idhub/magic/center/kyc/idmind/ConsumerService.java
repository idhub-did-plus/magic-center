package com.idhub.magic.center.kyc.idmind;

import com.idhub.magic.center.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.center.kyc.idmind.entity.consumer.ConsumerKycResponse;
import com.idhub.magic.center.parameter.MagicResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConsumerService {
	 @Headers({"Content-Type: application/json", "accept: application/json"})
	@POST("/im/account/consumer")
	 Call<ConsumerKycResponse> customer(@Body ConsumerKycRequest req,@Query(value = "graphScoreResponse") boolean graphScoreResponse);
}
//https://edoc.identitymind.com/reference#apioverview