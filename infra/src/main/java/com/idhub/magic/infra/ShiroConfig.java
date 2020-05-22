package com.idhub.magic.infra;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.idhub.magic.infra.access.SmartRealm;

@Configuration
public class ShiroConfig {
	/*
	 * @Bean SmartRealm myRealm() { return new SmartRealm(); }
	 */

	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	DefaultWebSecurityManager securityManager(SmartRealm rm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		//SmartRealm rm = myRealm();

		manager.setAuthorizer(rm);
		manager.setRealm(rm);
		return manager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/*
	 * @Bean ShiroFilterChainDefinition shiroFilterChainDefinition() {
	 * DefaultShiroFilterChainDefinition definition = new
	 * DefaultShiroFilterChainDefinition();
	 * //definition.addPathDefinition("/auth/login", "anon");
	 * //definition.addPathDefinition("/login.html", "login");
	 * //definition.addPathDefinition("/**", "authc"); return definition; }
	 */

	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login.html");

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/auth/login", "anon");
		//filterChainDefinitionMap.put("/issue_project/**", "roles[lawer]");
		filterChainDefinitionMap.put("/**", "authc");
		//filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

}