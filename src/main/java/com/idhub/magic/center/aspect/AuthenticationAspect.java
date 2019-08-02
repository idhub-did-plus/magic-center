package com.idhub.magic.center.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.idhub.magic.center.util.AuthenticationUtils;

@Aspect
@Configuration
public class AuthenticationAspect {
	@Autowired HttpServletRequest req;
	@Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void myBefore(JoinPoint joinpoint) {
		String signed = req.getHeader("signature");
		String qs = req.getQueryString();
		String identity = req.getParameter("identity");
		String timestamp = req.getParameter("timestamp");
		boolean authenticated = AuthenticationUtils.authenticate(signed, identity + timestamp, identity);
		if(!authenticated)
			throw new RuntimeException("....");
		
	}



}