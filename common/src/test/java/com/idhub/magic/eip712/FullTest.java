package com.idhub.magic.eip712;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import com.idhub.magic.common.eip712.CryptoUtils;
import com.idhub.magic.common.eip712.TypedData;
import com.idhub.magic.common.eip712.TypedDataSignature;




public class FullTest {

    @Test
    public void recoverSignedDataSignature1() {

	System.out.println("###########################################################");
	System.out.println("[START] recoverSignedDataSignature1");
	
	String signer = "0x00a329c0648769a73afac7f9381e08fb43dbea72";
	String signature = "0xdb34700d51e9cabe0b10518c35163bd5c551ea5eaa8946584fdc86a38b53ae115558e10abd82a726606bc3f07f8d84fcd3f60e24ec75ad6559c35ac1114676591c";
	TypedData t1 = new TypedData("field1", "string", "val1");
	TypedData t2 = new TypedData("field2", "string", "val2");
	
	byte[] result = TypedDataSignature.generateSignatureHash(Arrays.asList(t1, t2));
	System.out.println("data = "+ DatatypeConverter.printHexBinary(result));

	Map<Integer, String> addresses = CryptoUtils.ecrecover(signature, result);
	
	System.out.println(addresses.toString());
	
//	assertThat(addresses, IsMapContaining.hasValue(signer));
	
	System.out.println("[END] recoverSignedDataSignature1");
    }

    @Test
    public void recoverSignedDataSignature2() {

	System.out.println("###########################################################");
	System.out.println("[START] recoverSignedDataSignature2");
	
	String signer = "0x00a329c0648769a73afac7f9381e08fb43dbea72";
	String signature = "0x9125a986c4ea461e97166b86486f577d1186bd9b76f0a3c4f8f5e8c2563d7a7e1e816a85ee6235cceccc81a9fa0ba9ec88b95a985b6b19234812a9c2332db69700";
	TypedData t1 = new TypedData("field1", "string", "val1");
	TypedData t2 = new TypedData("field2", "uint", 5);
	TypedData t3 = new TypedData("field3", "bool", true);
	
	byte[] result = TypedDataSignature.generateSignatureHash(Arrays.asList(t1, t2, t3));
	System.out.println("data = "+ DatatypeConverter.printHexBinary(result));

	Map<Integer, String> addresses = CryptoUtils.ecrecover(signature, result);
	
	System.out.println(addresses.toString());
	
	//assertThat(addresses, IsMapContaining.hasValue(signer));
	
	System.out.println("[END] recoverSignedDataSignature2");
    }
    
}
