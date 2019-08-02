package com.idhub.magic.center.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.util.AuthenticationUtils;

@RestController
@RequestMapping("/proxy")

public class WalletProxyController {
    @PostMapping("/createIdentity")
	public String createIdentity(@RequestBody String json) {
    	 auth();
		return null;
	}
    @Autowired HttpServletRequest req;
	
	public void auth() {
		String signed = req.getHeader("signature");
		String identity = req.getParameter("identity");
		String timestamp = req.getParameter("timestamp");
		boolean authenticated = AuthenticationUtils.authenticate(signed, identity + timestamp, identity);
		if(!authenticated)
			throw new RuntimeException("....");
		
	}
}
