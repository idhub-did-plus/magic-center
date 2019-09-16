package com.idhub.magic.center.ustorage.entity.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressTemplate {
	static Map<String, List<AddressElement>> templates = new HashMap<String, List<AddressElement>>();
	static Map<String, List<String>> enumsets = new HashMap<String, List<String>>();
	static {
		templates.put("default", createTemplate("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		templates.put("us", createTemplate("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		templates.put("china", createTemplate("province","city","county","street", "residence","menpaihao"));
		//....
		enumsets.put("province@china", Arrays.asList("beijing","shanghai","tianjin"));
		//.....
	
	}
	
	static public List<AddressElement> template(String country){
		List<AddressElement> temp = templates.get(country);
		if(temp != null)
			return temp;
		return templates.get("default");
	}
	static List<AddressElement> createTemplate(String... names){
		List<AddressElement> rst = new ArrayList<AddressElement>();
		for(String name : names) {
			rst.add(new AddressElement(name));
		}
		return rst;
	}
	static List<String> enumset(String country, String element){
		return enumsets.get(element + "@" + country);
	}
}
