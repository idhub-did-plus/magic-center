package com.idhub.magic.provider.acl.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.common.parameter.MagicResponse;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired Datastore datastore;

    @Autowired HttpSession session;



    @GetMapping("/login")
    public MagicResponse login(String identity) {
    	
        Subject sub = SecurityUtils.getSubject();
        
        

        SignatureToken token = new SignatureToken("ddd", "ddd","eee");
          
        sub.login(token);
        String login = (String) SecurityUtils.getSubject().getPrincipal();
        return new MagicResponse();

    }

}

