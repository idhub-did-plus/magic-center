package com.idhub.magic.center.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idhub.magic.center.annotation.DoNotAuth;
import com.idhub.magic.center.entity.ClaimEntity;
import com.idhub.magic.center.entity.OrderEntity;
import com.idhub.magic.center.entity.OrderState;
import com.idhub.magic.center.ustorage.IdentityStorage;
import com.idhub.magic.center.ustorage.MaterialWrapper;
import com.idhub.magic.center.util.JwtTokenUtil;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.FinancialProfile;
import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.Material;
import com.idhub.magic.common.ustorage.entity.ext.ExtensionField;
import com.idhub.magic.provider.Order;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/thirdparty")

public class ThirdpartyController {
	@Autowired Datastore store;
	
	@PostMapping("/identity_information")
	@DoNotAuth
	public MagicResponse<IdentityInformation> retrieveArchive(@RequestBody String jwt, String identity) {
		boolean valid = JwtTokenUtil.validate(jwt);
		if(!valid)
			return new MagicResponse(false, "bad jwt token!");
		IdentityStorage st = store.find(IdentityStorage.class, "id", identity).get();
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "material.identity", identity);
		List<MaterialWrapper> data = query.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());

		Query<ClaimEntity> cquery = store.find(ClaimEntity.class, "identity", identity);
		List<ClaimEntity> cdata = cquery.asList();
		List<String> ld = cdata.stream().map(ClaimEntity::getClaimJsonld).collect(toList());
		return new MagicResponse<IdentityInformation>(new IdentityInformation(st.getIdentityArchive(), mdata, ld));
	}

	@GetMapping("/test")
	@DoNotAuth
	public MagicResponse test() {
	
		return new MagicResponse();
	}
	

	
}
class IdentityInformation{
	public IdentityArchive archive;
	public List<Material> materials;
	public IdentityInformation(IdentityArchive archive, List<Material> materials, List<String> claims) {
		super();
		this.archive = archive;
		this.materials = materials;
		this.claims = claims;
	}
	public List<String> claims;
}