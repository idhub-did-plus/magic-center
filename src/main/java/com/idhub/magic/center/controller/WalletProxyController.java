package com.idhub.magic.center.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alipay")

public class WalletProxyController {
    @PostMapping("/crreateIdentity")
	public String crreateIdentity(@RequestBody String json) {
		return null;
	}
}
