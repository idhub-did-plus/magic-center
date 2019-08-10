package com.idhub.magic.center.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idhub.magic.center.util.AuthenticationUtils;

//@Aspect
//@Configuration
public class LogAspect {
	@Autowired HttpServletRequest req;
	@Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void log(JoinPoint joinpoint) {
	
		
	}



}