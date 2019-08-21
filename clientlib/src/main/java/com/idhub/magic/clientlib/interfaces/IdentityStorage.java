package com.idhub.magic.clientlib.interfaces;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IdentityStorage {
	 @POST("/storeArchive")
     @FormUrlEncoded
     Call<MagicResponse> storeArchive(IdentityArchive archive, @Query("cate")String identity);
	 @GET("retrieve_archive")
	 Call<MagicResponse<IdentityArchive>>  retrieveArchive(@Query("cate")String identity);

     @POST("/load_materiale")
     @Multipart
     Call<MagicResponse> uploadMaterial(@Query("cate")String identity, @Part("name") RequestBody name, @Part("type") RequestBody age, @Part MultipartBody.Part file);

}
