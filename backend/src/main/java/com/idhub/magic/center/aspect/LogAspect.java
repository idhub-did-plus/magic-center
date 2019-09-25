package com.idhub.magic.center.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.idhub.magic.common.parameter.MagicResponse;

@Aspect
@Configuration
@Order(1)
public class LogAspect {
	@Autowired HttpServletRequest req;
	@Around("@annotation(org.springframework.web.bind.annotation.PostMapping)||@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public Object onActivityMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	 
	    try {
			Object rst = proceedingJoinPoint.proceed();
			return rst;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MagicResponse(false, e.getMessage());
		}
	    
	}


}