package com.idhub.magic.center.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.service.TemplateData;
import com.idhub.magic.center.ustorage.entity.component.AddressElement;

@RestController
@RequestMapping("/template")

public class TemplateController {
	static TemplateData data;
	static {
	/*	String identity = ProviderFactory.getProvider().getDefaultCredentials().getAddress();
		try {
			data = ApiFactory.getTemplateService().templateData(identity).execute().body().getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data = fromStorage();
		}*/
		data = new TemplateData();
		data.templates.put("default", createTemplate("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		data.templates.put("us", createTemplate("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		data.templates.put("china", createTemplate("province","city","county","street", "residence","menpaihao"));
		//....
		data.enumsets.put("province@china", Arrays.asList("beijing","shanghai","tianjin"));
		//.....
	
	}
	static List<AddressElement> createTemplate(String... names){
		List<AddressElement> rst = new ArrayList<AddressElement>();
		for(String name : names) {
			rst.add(new AddressElement(name));
		}
		return rst;
	}
	
	
	@GetMapping("/template_data")
	public MagicResponse<TemplateData> retrieveArchive(String identity) {
		
		return new MagicResponse<TemplateData>(data);
	}
}
