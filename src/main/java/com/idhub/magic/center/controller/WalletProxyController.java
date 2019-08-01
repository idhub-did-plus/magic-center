package com.idhub.magic.center.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alipay")

public class WalletProxyController {
    @PostMapping("/crreateIdentity.json")
	public String crreateIdentity(String identity, String json) {
		return null;
	}
}
