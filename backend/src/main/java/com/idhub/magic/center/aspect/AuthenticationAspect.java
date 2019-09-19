package com.idhub.magic.center.aspect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.common.util.AuthenticationUtils;
import com.idhub.magic.common.util.Signature;

@Aspect
@Configuration
public class AuthenticationAspect {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired HttpServletRequest req;
	@Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void myBefore(JoinPoint joinpoint) {
		String signed = req.getHeader("signature");
		String identity = req.getParameter("identity");
		String timestamp = req.getParameter("timestamp");
		Signature signature;
		try {
			signature = mapper.readValue(signed, Signature.class);
			boolean authenticated = AuthenticationUtils.authenticate(signature, identity + timestamp, identity);
			if(!authenticated)
				throw new RuntimeException("Authentication failed!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		
		
	}



}