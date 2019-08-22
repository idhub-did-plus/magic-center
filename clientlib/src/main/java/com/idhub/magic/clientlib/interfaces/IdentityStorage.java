package com.idhub.magic.clientlib.interfaces;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IdentityStorage {
	 @POST("storage/store_archive")
     Call<MagicResponse> storeArchive(@Body IdentityArchive archive, @Query("identity")String identity);
	 @GET("storage/retrieve_archive")
	 Call<MagicResponse<IdentityArchive>>  retrieveArchive(@Query("identity")String identity);

     @POST("storage/upload_material")
     @Multipart
     Call<MagicResponse> uploadMaterial(@Query("identity")String identity, @Part("name") RequestBody name, @Part("type") RequestBody type, @Part MultipartBody.Part file);

}
