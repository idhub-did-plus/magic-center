package com.idhub.magic.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Hash;

import com.idhub.magic.common.claim.Claim;
import com.idhub.magic.common.claim.VerifiableClaim;

@Service
public class BlockchainService {
	@Autowired ContractManager contracts;
	public void publishTo780(VerifiableClaim claim) {
		byte[][][] encoded = encode(claim.getClaim());
		try {
			for(byte[][] kv : encoded) {
				contracts.registry780.setClaim(claim.getSubject(), kv[0], kv[1]).send();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	byte[][][] encode(Claim claim){
		byte[][][] rst = new byte[3][2][];
		rst[0][0] = Hash.sha3("claimType".getBytes());
		rst[0][1] = Hash.sha3(claim.getClaimType().getBytes());
		rst[1][0] = Hash.sha3("country".getBytes());
		rst[1][1] = Hash.sha3(claim.getCountry().getBytes());
		rst[2][0] = Hash.sha3("jurisdiction".getBytes());
		rst[2][1] = Hash.sha3(claim.getJurisdiction().getBytes());
		return rst;
	}
	
}
