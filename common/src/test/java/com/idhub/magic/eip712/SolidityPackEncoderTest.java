package com.idhub.magic.eip712;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;

import com.idhub.magic.common.eip712.SolidityPackEncoder;


public class SolidityPackEncoderTest {

    @Test
    public void encodeUtf8StringTypeTest() {
	System.out.println("###########################################################");
	System.out.println("[START] encodeUtf8StringTypeTest");
	String val = "val1";
	Utf8String field = new Utf8String(val);
	
	byte[] result = SolidityPackEncoder.encodeType(field);
	System.out.println(new String(result));
	
	assertEquals(DatatypeConverter.printHexBinary(val.getBytes()), DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] encodeUtf8StringTypeTest");
    }
    
    @Test
    public void encodeBoolTypeTest() {
	System.out.println("###########################################################");
	System.out.println("[START] encodeBoolTypeTest");
	Boolean val = true;
	Bool field = new Bool(val);
	
	byte[] result = SolidityPackEncoder.encodeType(field);
	System.out.println(DatatypeConverter.printHexBinary(result));
	
	assertEquals(DatatypeConverter.printHexBinary(new byte[] {(byte)1}), DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] encodeBoolTypeTest");
    }
    
    @Test
    public void encodeNumericTypeTest() {
	System.out.println("###########################################################");
	System.out.println("[START] encodeBoolTypeTest");
	String val = "5";
	Uint field = new Uint(new BigInteger(val));
	
	byte[] result = SolidityPackEncoder.encodeType(field);
	System.out.println(DatatypeConverter.printHexBinary(result));
	
	assertEquals("0000000000000000000000000000000000000000000000000000000000000005", DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] encodeBoolTypeTest");
    }
    
    @Test
    public void solidityPackTest1() {
	System.out.println("###########################################################");
	System.out.println("[START] solidityPackTest1");
	String val1 = "val1";
	Utf8String field1 = new Utf8String(val1);
	String val2 = "val2";
	Utf8String field2 = new Utf8String(val2);
	
	
	byte[] result = SolidityPackEncoder.solidityPack(Arrays.asList(field1, field2));
	System.out.println(DatatypeConverter.printHexBinary(result));
	
	assertEquals(DatatypeConverter.printHexBinary((val1+val2).getBytes()), DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] solidityPackTest1");
    }
    
    @Test
    public void solidityPackTest2() {
	System.out.println("###########################################################");
	System.out.println("[START] solidityPackTest2");
	Utf8String field1 = new Utf8String("val1");
	Uint field2 = new Uint(new BigInteger("5"));
	Bool field3 = new Bool(true);
	
	
	
	byte[] result = SolidityPackEncoder.solidityPack(Arrays.asList(field1, field2, field3));
	System.out.println(DatatypeConverter.printHexBinary(result));
	
	assertEquals(
		"76616C31000000000000000000000000000000000000000000000000000000000000000501", 
		DatatypeConverter.printHexBinary(result));
	
	System.out.println("[END] solidityPackTest2");
    }
    
    @Test
    public void soliditySha3Test() {
	System.out.println("###########################################################");
	System.out.println("[START] soliditySha3Test");
	Utf8String field1 = new Utf8String("val1");
	Uint field2 = new Uint(new BigInteger("5"));
	Bool field3 = new Bool(true);
	
	
	
	byte[] hash = SolidityPackEncoder.soliditySHA3(Arrays.asList(field1, field2, field3));
	System.out.println(DatatypeConverter.printHexBinary(hash));
	
	assertEquals(
		"9918c2158639e8f34d0b94768e9701c900bd932dcb79d76c88dfeab17019c451", 
		DatatypeConverter.printHexBinary(hash).toLowerCase());
	
	System.out.println("[END] soliditySha3Test");
    }
    
    
    
}
