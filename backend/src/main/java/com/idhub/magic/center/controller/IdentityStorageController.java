package com.idhub.magic.center.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

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
import com.idhub.magic.center.service.IdentityAggregationService;
import com.idhub.magic.center.service.MyIden;
import com.idhub.magic.center.service.SimpleStorageService;
import com.idhub.magic.center.ustorage.IdentityStorage;
import com.idhub.magic.center.ustorage.MaterialWrapper;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.FinancialProfile;
import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.IdentityEverything;
import com.idhub.magic.common.ustorage.entity.Material;
import com.idhub.magic.common.ustorage.entity.ext.ExtensionField;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/storage")

public class IdentityStorageController {
	@Autowired Datastore store;
	@Autowired IdentityAggregationService identityAggregationService;
	@Autowired SimpleStorageService simpleStorageService;
	@PostMapping("/store_archive")
	public MagicResponse storeArchive(@RequestBody IdentityArchive archive,	String identity) {
		boolean notexists = store.createQuery(IdentityStorage.class).field("id").equal(identity).asList().isEmpty();
		if(notexists) {
			IdentityStorage st = new IdentityStorage();
			st.setId(identity);
			st.setIdentityArchive(archive);
			store.save(st);
			return new MagicResponse();
		}
		
		Query<IdentityStorage> query = store.find(IdentityStorage.class, "id", identity);
		UpdateOperations<IdentityStorage> up = store.createUpdateOperations(IdentityStorage.class).set("identityArchive", archive);
		store.update(query, up);
		return new MagicResponse();
	}
	@PostMapping("/store_financial_profile")
	public MagicResponse storeFinancialProfile(@RequestBody FinancialProfile profile,	String identity) {
		Query<IdentityStorage> query = store.find(IdentityStorage.class, "id", identity);
		UpdateOperations<IdentityStorage> up = store.createUpdateOperations(IdentityStorage.class).set("identityArchive.financialProfile", profile);
		UpdateResults rst = store.update(query, up);
		return new MagicResponse();
	}
	@GetMapping("/retrieve_archive")
	public MagicResponse<IdentityArchive> retrieveArchive(String identity) {
		IdentityStorage st = store.find(IdentityStorage.class, "id", identity).get();
		return new MagicResponse<IdentityArchive>(st.getIdentityArchive());
	}

	@PostMapping("/upload_material")
	@ResponseBody
	public MagicResponse upload(@RequestParam("file") MultipartFile file, String identity, String type, String name) throws Exception {
		if (file.isEmpty()) {
			return new MagicResponse(false, "upload fail!");
		}
		//byte[] data = IOUtils.toByteArray(file.getInputStream());
		String ext = extension(file);
		Material mat = new Material( identity,  type,  name,  ext);
		MaterialWrapper wr = new MaterialWrapper(mat);
		simpleStorageService.store(file, wr.getId());
		store.save(wr);
		
		return new MagicResponse();
	}
	private String extension(MultipartFile file) {
		String fname = file.getOriginalFilename();
		String[] ne = fname.split("\\.");
		String ext = ne[ne.length - 1];
		return ext;
	}
	@GetMapping("/retrieve_materials")
	@ResponseBody
	public MagicResponse<List<Material>> retrieveMaterials(String identity) {
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "material.identity", identity);
		List<MaterialWrapper> data = query.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());

		MagicResponse<List<Material>> rst = new MagicResponse<List<Material>>();
		rst.setData(mdata);
		return rst;
	}
	
	@GetMapping("/remove_material")
	@ResponseBody
	public MagicResponse removeMaterial(String identity, String type, String name) {
		String id = identity + type + name;
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "id", id);
		store.delete(query);
		MagicResponse rst = new MagicResponse();

		return rst;
	}
	@GetMapping("/material_stream")
	@DoNotAuth
	public void materialStream(String identity, String type, String name, HttpServletResponse response) throws Exception {
		String id = identity + type + name;
		stream(response, id);
		
		
	}
	@GetMapping("/material_stream_id")
	@DoNotAuth
	private void stream(HttpServletResponse response, String id) throws IOException {
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "id", id);
		
		MaterialWrapper wrapper = query.get();
		
		String ext = wrapper.getMaterial().getExt();
		if(ext == null)
			ext = "jpg";
		if(ext.equalsIgnoreCase("jpg")) {
			response.setContentType("image/jpeg");
		}else {
			response.setContentType("application/pdf");
		}
		this.simpleStorageService.stream(wrapper.getId(), response.getOutputStream());
		/*
		 * byte[] data = getData(wrapper); response.getOutputStream().write(data);
		 * response.getOutputStream().flush();
		 */
	}
	private byte[] getData(MaterialWrapper wrapper) {
		
		byte[] data = this.simpleStorageService.get(wrapper.getId());//wrapper.getMaterial().getData();
	//	byte[] data = wrapper.getMaterial().getData();
		return data;
	}
	@GetMapping("/extension_meta")
	@ResponseBody
	public MagicResponse extentionMeta() {
		List<ExtensionField> ext = new ArrayList<ExtensionField>();
		MagicResponse rst = new MagicResponse(ext);

		return rst;
	}
	@GetMapping("/aggregate_identity")
	@ResponseBody
	public MagicResponse aggregateIdentity(String identity, long ein) {
		Query<IdentityStorage> query = store.find(IdentityStorage.class, "id", identity);
		UpdateOperations<IdentityStorage> up = store.createUpdateOperations(IdentityStorage.class).set("ein", ein);
		UpdateResults rst = store.update(query, up);
		return new MagicResponse();

	}
	@GetMapping("/recover")
	@ResponseBody
	public MagicResponse<IdentityEverything> recover(String identity) {
		long ein = this.identityAggregationService.ein(identity);
		IdentityEverything e = extractAll(ein);
		if(e == null)
			return new MagicResponse(false, "no aggregated data for this ein!");
		return new MagicResponse<IdentityEverything>(e);
	}
	IdentityEverything extractAll(long ein){
		
		IdentityStorage st = store.find(IdentityStorage.class, "ein", ein).get();
		if(st == null)
			return null;
		String identity = st.getId();
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "material.identity", identity);
		List<MaterialWrapper> data = query.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());

		Query<ClaimEntity> cquery = store.find(ClaimEntity.class, "identity", identity);
		List<ClaimEntity> cdata = cquery.asList();
		List<String> ld = cdata.stream().map(ClaimEntity::getClaimJsonld).collect(toList());
		return (new IdentityEverything(st.getIdentityArchive(), mdata, ld));
	}
	@GetMapping("/destry_legacy")
	@ResponseBody
	public MagicResponse destry_legacy(String identity,String legacyAddress) {
		IdentityStorage now = store.find(IdentityStorage.class, "id", identity).get();
		if(now == null|| now.getEin() <=0)
			return new MagicResponse(false, "please recover data before destroy old one!");
		IdentityStorage st = store.find(IdentityStorage.class, "id", legacyAddress).get();
		if(now.getEin() != st.getEin())
			return new MagicResponse(false, "can not destry data with different ein!");
		MyIden iden = this.identityAggregationService.associates(identity);	
		if(iden.getEin() != now.getEin())
			return new MagicResponse(false, "ein in 1484 not the same with this account!!");
		if( iden.getAssociates().contains(legacyAddress))
			return new MagicResponse(false, "legacy address still in 1484 associates !!");
		destroy(legacyAddress);
		
		return new MagicResponse();
	}
	private void destroy(String legacyAddress) {
		Query<IdentityStorage> q1 = store.find(IdentityStorage.class, "id", legacyAddress);
		Query<MaterialWrapper> q2 = store.find(MaterialWrapper.class, "material.identity", legacyAddress);
		Query<ClaimEntity> q3 = store.find(ClaimEntity.class, "identity", legacyAddress);
		store.delete(q1);
		store.delete(q2);
		store.delete(q3);
		
		
	}

}
