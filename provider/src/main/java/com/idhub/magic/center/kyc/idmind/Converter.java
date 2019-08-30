package com.idhub.magic.center.kyc.idmind;

import com.idhub.magic.center.kyc.idmind.entity.customer.CustomerKycRequest;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;

public class Converter {
	static public CustomerKycRequest archve2request(IdentityArchive archive){
		CustomerKycRequest req = new CustomerKycRequest();
		//req.man = "req.
		return dummy();
	}

	static public CustomerKycRequest dummy() {
		CustomerKycRequest req = new CustomerKycRequest();
		req.man = "jdoe";
		req.soc = "facebook";
		req.tea = "jdoe1964@gmail.com";
		req.bc = "Jersey City";
		req.bco = "US";
		req.bfn = "John";
		req.bln = "Doe";
		req.bz = "07304";
		req.bs = "NJ";
		req.bsn = "123 1st St";
		req.dft = "AU";
		req.dfp = "187ASDSA&#EAD129E…";
		req.ip = "72.160.112.466";
		req.pbc = "89756415ac…";
		req.ptoken = "4697XXXXXX000e";

		return req;
	}

}
