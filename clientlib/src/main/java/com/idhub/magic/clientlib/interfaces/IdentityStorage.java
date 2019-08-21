package com.idhub.magic.clientlib.interfaces;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdentityStorage {
	void storeArchive(IdentityArchive archive);
	 @GET("retrieve_archive?identity={identity}")
	 Call<MagicResponse<IdentityArchive>>  retrieveArchive(String identity);
}
