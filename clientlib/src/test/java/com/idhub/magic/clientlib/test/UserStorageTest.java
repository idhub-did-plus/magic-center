package com.idhub.magic.clientlib.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.ustorage.entity.FinancialProfile;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.IdentityInfo;
import com.idhub.magic.center.ustorage.entity.Material;
import com.idhub.magic.clientlib.ApiFactory;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

class UserStorageTest {
	IdentityStorage storage;
	String identity;
	@BeforeEach
	void setUp() throws Exception {
		storage = ApiFactory.getArchiveStorage();
		identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
	}
	IdentityArchive dummyArchive()
	{
		IdentityArchive ida = new IdentityArchive();
		IdentityInfo ii = new IdentityInfo();
		ii.setBirthday(new Date());
		ii.setCountry("china");
		//ii.setFirstName("yuqi");
		//ii.setLastName("bai");
		ii.setPassportNumber("ggggg");
		ida.setIdentityInfo(ii);
		return ida;
	}
	@Test
	void testStoreArchive() throws Exception {
		IdentityArchive ida = dummyArchive();
		MagicResponse mss = storage.storeArchive(ida, identity).execute().body();

	}
	@Test
	void testStoreFinancialProfile() throws Exception {
		IdentityArchive ida = dummyArchive();
		MagicResponse resp = storage.storeFinancialProfile(new FinancialProfile(), identity).execute().body();


	}
	@Test
	void testuploadMaterial() throws Exception {
		File file = new File("timg.jpg");
		  MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

		IdentityArchive ida = dummyArchive();
		MagicResponse resp = storage.uploadMaterial(identity, "ddd", "ddd", filePart).execute().body();

	}
	@Test
	void testRetrieveArchive() throws Exception {
			MagicResponse<IdentityArchive> user = storage.retrieveArchive(identity).execute().body();

	}
	@Test
	void testRetrieveMaterials() throws Exception {
		 MagicResponse<List<Material>> ddd = storage.retrieveMaterials(identity).execute().body();

	}
	@Test
	void testRemoveMaterials() throws Exception {
		MagicResponse ms = storage.removeMaterial(identity, "kkk", "lll").execute().body();

	}
	@Test
	void testExtensionMeta() throws Exception {
		MagicResponse ms = storage.extensionMeta(identity).execute().body();

	}
}
