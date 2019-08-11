package com.idhub.magic.center.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

//@Aspect
//@Configuration
public class LogAspect {
	@Autowired HttpServletRequest req;
	@Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void log(JoinPoint joinpoint) {
	
		
	}



}