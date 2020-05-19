package com.idhub.magic.infra.controller;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired Datastore datastore;
    @GetMapping("/login")
	public MagicResponse login(String action, String identity, String timestamp, String claim, String signature) {
		return new MagicResponse();
	}
    @GetMapping("/logout")
   	public MagicResponse logout() {
   		return new MagicResponse();
   	}
}
