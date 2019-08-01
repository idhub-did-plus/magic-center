package com.idhub.magic.center.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idhub.magic.center.util.Utils;

//切面类
@Aspect
@Component
public class AuthenticationAspect {
	@Autowired HttpServletRequest req;
	@Before("myPointCut()")
	public void myBefore(JoinPoint joinpoint) {
		String signed = req.getHeader("signed");
		String qs = req.getQueryString();
		String identity = req.getParameter("identity");
		String timestamp = req.getParameter("timestamp");
		boolean authenticated = Utils.authenticate(signed, identity + timestamp);
		if(!authenticated)
			throw new RuntimeException("....");
		
	}



}