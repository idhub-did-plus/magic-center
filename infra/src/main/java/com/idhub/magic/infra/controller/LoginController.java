package com.idhub.magic.infra.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.infra.access.SignatureToken;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired Datastore datastore;
    @GetMapping("/login")
	public MagicResponse login(String action, String identity, String timestamp, String claim, String signature) {
	
    	
     Subject sub = SecurityUtils.getSubject();
        
        

        SignatureToken token = new SignatureToken(identity, timestamp + identity  ,signature);
          
        sub.login(token);
      //  SecurityUtils.getSubject().checkRole("lawer");
        return new MagicResponse();

	}
    @GetMapping("/logout")
   	public MagicResponse logout() {
   		return new MagicResponse();
   	}
}
