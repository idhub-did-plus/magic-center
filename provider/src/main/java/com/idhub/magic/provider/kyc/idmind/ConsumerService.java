package com.idhub.magic.provider.kyc.idmind;

import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsumerService {
	 @Headers({"Content-Type: application/json", "accept: application/json"})
	@POST("/im/account/consumer")
	 Call<ConsumerKycResponse> customer(@Body ConsumerKycRequest req,@Query(value = "graphScoreResponse") boolean graphScoreResponse);
	 @GET("/im/account/consumer/v2/{mtid}")
	 Call<ConsumerKycResponse> retrievestate(@Path("mtid") String mtid);
	 @POST("/im/account/consumer/{appId}/dv")
     @Multipart
     Call<Void> uploadDodumentVerification(@Path("appId")String appid, @Part("description") String description, @Part MultipartBody.Part file);
}
//https://edoc.identitymind.com/reference#apioverview