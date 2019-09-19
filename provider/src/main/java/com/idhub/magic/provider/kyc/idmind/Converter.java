package com.idhub.magic.provider.kyc.idmind;

import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;

public class Converter {
	static public ConsumerKycRequest archve2request(IdentityArchive archive){
		ConsumerKycRequest req = new ConsumerKycRequest();
		//req.man = "req.
		return dummy();
	}

	static public ConsumerKycRequest dummy() {
		ConsumerKycRequest req = new ConsumerKycRequest();
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
