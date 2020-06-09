package com.idhub.magic.infra.controller;

import javax.validation.constraints.NotNull;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.infra.access.SignatureToken;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	Datastore datastore;

	@GetMapping("/login")
	public MagicResponse login(String action, String identity, String timestamp, String claim, String signature) {

		Subject sub = SecurityUtils.getSubject();
		if (action != null && action.equals("reentry")) {
			if (sub.isAuthenticated()) {

				return success(claim);
			} else {

				return fail();
			}
		}
		if (sub.hasRole(claim)) {

			return success(claim);
		}

		SignatureToken token = new SignatureToken(identity, claim, timestamp, signature);

		sub.login(token);
		sub.checkRole(claim);

		return success(claim);

	}

	@GetMapping("/logout")
	public MagicResponse logout() {
		Subject sub = SecurityUtils.getSubject();
		sub.logout();
		return new MagicResponse();
	}

	@GetMapping("/notice")
	public MagicResponse notice() {
		return fail();
	}

	MagicResponse success(String claim) {
		MagicResponse resp = new MagicResponse();
		resp.setMessage("login successful!");
		resp.setClaim(claim);
		return resp;
	}

	MagicResponse fail() {
		MagicResponse resp = new MagicResponse();
		resp.setMessage("Please login first!");
		resp.setSuccess(false);
		return resp;
	}
}
