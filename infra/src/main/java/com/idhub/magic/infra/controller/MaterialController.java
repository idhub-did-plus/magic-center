package com.idhub.magic.infra.controller;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.idhub.magic.infra.model.DeployedToken;
import com.idhub.magic.infra.model.IssueProject;
import com.idhub.magic.infra.model.IssuerInformation;
import com.idhub.magic.infra.model.ProjectMaterial;
import com.idhub.magic.infra.model.ProjectStatus;
import com.idhub.magic.infra.model.TokenConfig;
import com.idhub.magic.infra.service.MongoStorageService;
import com.idhub.magic.infra.service.SimpleStorageService;
import com.idhub.magic.infra.service.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/material")
public class MaterialController {

	@Autowired
	Datastore ds;
	@Autowired
	private SimpleStorageService storageService;

	
	@PostMapping("/upload_material")
	@ResponseBody
	public MagicResponse<ProjectMaterial> upload(@RequestParam("file") MultipartFile file, String pid, String type, String name,String content)
			throws Exception {
		if (file.isEmpty()) {
			return new MagicResponse(false, "upload fail!");
		}
		// byte[] data = IOUtils.toByteArray(file.getInputStream());
		String ext = extension(file);
		ProjectMaterial mat = new ProjectMaterial(pid, type, name, ext, content);
		byte[] data = file.getBytes();
		String md5Hex = DigestUtils.md5Hex(data).toUpperCase();
		mat.setHash(md5Hex);
		storageService.store(data, mat.getId());
		ds.save(mat);

		return new MagicResponse<ProjectMaterial>(mat);
	}

	private String extension(MultipartFile file) {
		String fname = file.getOriginalFilename();
		String[] ne = fname.split("\\.");
		String ext = ne[ne.length - 1];
		return ext;
	}
	@GetMapping("/retrieve_materials")
	@ResponseBody
	public MagicResponse<List<ProjectMaterial>> retrieveMaterials(String pid) {
		Query<ProjectMaterial> query = ds.find(ProjectMaterial.class, "projectId", pid);
		List<ProjectMaterial> data = query.asList();
		
		MagicResponse<List<ProjectMaterial>> rst = new MagicResponse<List<ProjectMaterial>>();
		rst.setData(data);
		return rst;
	}
	
	@GetMapping("/remove_material")
	@ResponseBody
	public MagicResponse removeMaterial(String id) {

		Query<ProjectMaterial> query = ds.find(ProjectMaterial.class, "id", id);
		ds.delete(query);
		MagicResponse rst = new MagicResponse();

		return rst;
	}
	@GetMapping("/onchain")
	@ResponseBody
	public MagicResponse onchain(String id) {

		Query<ProjectMaterial> query = ds.find(ProjectMaterial.class, "id", id);
		UpdateOperations<ProjectMaterial> up = ds.createUpdateOperations(ProjectMaterial.class).set("onchainTime", new Date()).set("onchain", true);
		ds.update(query, up);
		MagicResponse rst = new MagicResponse();

		return rst;
	}
	@GetMapping("/material_stream_id")
	//@DoNotAuth
	private void stream(HttpServletResponse response, String id) throws IOException {
		Query<ProjectMaterial> query = ds.find(ProjectMaterial.class, "id", id);
		
		ProjectMaterial mat = query.get();
		
		String ext = mat.getExt();
		if(ext == null)
			ext = "jpg";
		if(ext.equalsIgnoreCase("jpg")) {
			response.setContentType("image/jpeg");
		}else {
			response.setContentType("application/pdf");
		}
		this.storageService.stream(mat.getId(), response.getOutputStream());
		/*
		 * byte[] data = getData(wrapper); response.getOutputStream().write(data);
		 * response.getOutputStream().flush();
		 */
	}
	private byte[] getData(ProjectMaterial wrapper) {
		
		byte[] data = this.storageService.get(wrapper.getId());//wrapper.getMaterial().getData();
	//	byte[] data = wrapper.getMaterial().getData();
		return data;
	}


}
