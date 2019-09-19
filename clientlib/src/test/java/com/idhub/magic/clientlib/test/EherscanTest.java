package com.idhub.magic.clientlib.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.clientlib.ApiFactory;
import com.idhub.magic.clientlib.ProviderFactory;
import com.idhub.magic.clientlib.event.EventFetcher;
import com.idhub.magic.clientlib.http.RetrofitAccessor;
import com.idhub.magic.clientlib.interfaces.EventService;
import com.idhub.magic.clientlib.interfaces.IdentityChainDelegate;
import com.idhub.magic.clientlib.interfaces.IdentityStorage;
import com.idhub.magic.common.event.MagicEvent;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.FinancialProfile;
import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.IdentityInfo;
import com.idhub.magic.common.ustorage.entity.Material;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

class EtherscanTest {
	ObjectMapper mapper = new ObjectMapper();
	String identity;
	IdentityChainDelegate service;
	@BeforeEach
	void setUp() throws Exception {
		EventFetcher.getInstance();
		service = ApiFactory.getIdentityChainDelegate();
			
		identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
	}
	@Test
	void testDummyEvent() throws Exception
	{

			

	}
}
