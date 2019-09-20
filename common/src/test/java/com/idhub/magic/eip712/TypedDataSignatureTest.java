package com.idhub.magic.eip712;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.web3j.abi.datatypes.Utf8String;

import com.idhub.magic.common.eip712.TypedData;
import com.idhub.magic.common.eip712.TypedDataSignature;


public class TypedDataSignatureTest {

    @Test
    public void generateSignatureHash2stringsTest() {

	System.out.println("###########################################################");
	System.out.println("[START] generateSignatureHash2stringsTest");
	
	TypedData t1 = new TypedData("field1", "string", "val1");
	TypedData t2 = new TypedData("field2", "string", "val2");
	
	byte[] result = TypedDataSignature.generateSignatureHash(Arrays.asList(t1, t2));

	System.out.println(DatatypeConverter.printHexBinary(result));
	
	//assertEquals(DatatypeConverter.printHexBinary(new byte[] {(byte)1}), DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] generateSignatureHash2stringsTest");
    }

    @Test
    public void generateSignatureHash1string1bool1uintTest() {

	System.out.println("###########################################################");
	System.out.println("[START] generateSignatureHash1string1bool1uintTest");
	
	TypedData t1 = new TypedData("field1", "string", "val1");
	TypedData t2 = new TypedData("field2", "uint", 5);
	TypedData t3 = new TypedData("field3", "bool", true);
	
	byte[] result = TypedDataSignature.generateSignatureHash(Arrays.asList(t1, t2, t3));

	System.out.println(DatatypeConverter.printHexBinary(result));
	
	//assertEquals(DatatypeConverter.printHexBinary(new byte[] {(byte)1}), DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] generateSignatureHash1string1bool1uintTest");
    }
}
