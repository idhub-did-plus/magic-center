package com.idhub.magic.provider.acl.controller;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


//@Service("authorizer")
public class SmartRealm extends AuthorizingRealm {

	
	public SmartRealm() {
		super();
		this.setAuthenticationTokenClass(SignatureToken.class);
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		info.addRole("lawer");

		return info;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		SignatureToken token = (SignatureToken) authcToken;
//		User user = loadUserByLoginName(token.getUsername(), new String(token.getPassword()));
		
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
	}
	
   
}
