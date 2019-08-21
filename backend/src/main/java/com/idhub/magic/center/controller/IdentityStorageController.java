package com.idhub.magic.center.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idhub.magic.center.ustorage.IdentityStorage;
import com.idhub.magic.center.ustorage.MaterialWrapper;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.Material;
import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/storage")

public class IdentityStorageController {
	@Autowired Datastore store;
	@PostMapping("/storeArchive")
	public MagicResponse storeArchive(@RequestBody IdentityArchive archive,	String identity) {
		Key<?> id = store.exists(identity);
		if(id == null) {
			IdentityStorage st = new IdentityStorage();
			st.setIdentity(identity);
			st.setIdentityArchive(archive);
			store.save(st);
			return new MagicResponse();
		}
		
		Query<IdentityStorage> query = store.find(IdentityStorage.class, "id", identity);
		UpdateOperations<IdentityStorage> up = store.createUpdateOperations(IdentityStorage.class).set("archive", archive);
		store.update(query, up);
		return new MagicResponse();
	}
	@GetMapping("/retrieveArchive")
	public MagicResponse retrieveArchive(String identity) {
		IdentityStorage st = store.find(IdentityStorage.class, "id", identity).get();
		return new MagicResponse(st.getIdentityArchive());
	}

	@PostMapping("/upload_material")
	@ResponseBody
	public MagicResponse upload(@RequestParam("file") MultipartFile file, String identity, String type, String name) throws Exception {
		if (file.isEmpty()) {
			return new MagicResponse(false, "upload fail!");
		}
		byte[] data = IOUtils.toByteArray(file.getInputStream());
	
		Material mat = new Material( identity,  type,  name, data);
		MaterialWrapper wr = new MaterialWrapper(mat);
		store.save(mat);
		
		return new MagicResponse();
	}
	@PostMapping("/retieve_materials")
	@ResponseBody
	public MagicResponse retrieveMaterials(String identity) {
		Query<MaterialWrapper> query = store.find(MaterialWrapper.class, "material.identity", identity);
		List<MaterialWrapper> data = query.asList();
		List<Material> mdata = data.stream().map(MaterialWrapper::getMaterial).collect(toList());

		MagicResponse rst = new MagicResponse();
		rst.setData(mdata);
		return rst;
	}
	
	@PostMapping("/retieve_materials")
	@ResponseBody
	public MagicResponse remove(String identity, String type, String name) {
		String id = identity + type + name;
		Query<Material> query = store.find(Material.class, "id", id);
		store.delete(query);
		MagicResponse rst = new MagicResponse();

		return rst;
	}
}
