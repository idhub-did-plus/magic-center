package com.idhub.magic.center.controller;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.util.Utils;

@RestController
@RequestMapping("/proxy")

public class WalletProxyController {
    @PostMapping("/crreateIdentity")
	public String crreateIdentity(@RequestBody String json) {
    	 auth();
		return null;
	}
    @Autowired HttpServletRequest req;
	
	public void auth() {
		String signed = req.getHeader("signature");
		String qs = req.getQueryString();
		String identity = req.getParameter("identity");
		String timestamp = req.getParameter("timestamp");
		boolean authenticated = Utils.authenticate(signed, identity + timestamp, identity);
		if(!authenticated)
			throw new RuntimeException("....");
		
	}
}
