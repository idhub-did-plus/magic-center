package com.idhub.magic.center.ustorage.entity.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressTemplate {
	static Map<String, List<AddressElement>> templates = new HashMap<String, List<AddressElement>>();
	static {
		templates.put("default", create("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		templates.put("us", create("state","city","neighbourhood","streetNumber", "houseNumber", "apartmentNumber"));
		templates.put("china", create("province","city","county","street", "residence","menpaihao"));
	}
	static public List<AddressElement> template(String country){
		List<AddressElement> temp = templates.get(country);
		if(temp != null)
			return temp;
		return templates.get("default");
	}
	static List<AddressElement> create(String... names){
		List<AddressElement> rst = new ArrayList<AddressElement>();
		for(String name : names) {
			rst.add(new AddressElement(name));
		}
		return rst;
	}
}
