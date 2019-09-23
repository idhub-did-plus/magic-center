package com.idhub.magic.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSequenceGenerator;

public class JsonldUtils {
	 static  public String encodeSignature(byte[] r, byte[] s, byte[] v) {
         try {
			// Usually 70-72 bytes.
			  ByteArrayOutputStream bos = new ByteArrayOutputStream(72);
			  DERSequenceGenerator seq = new DERSequenceGenerator(bos);
			  seq.addObject(new ASN1Integer(r));
			  seq.addObject(new ASN1Integer(s));
			  seq.addObject(new ASN1Integer(v));
			  seq.close();
			  String rst = Base64.getEncoder().encodeToString(bos.toByteArray());
			  return rst;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
     }
	 static public List<byte[]> decodeSignature(String signature) {
		 byte[] data = Base64.getDecoder().decode(signature);
			try {
				List<byte[]> rst = new ArrayList<byte[]>();
				 ASN1StreamParser parser = new ASN1StreamParser(data);
				 ASN1Encodable hh = parser.readObject();
				 DERSequence p = (DERSequence) hh.toASN1Primitive();
				 ASN1Encodable[] as = p.toArray();
				 for(ASN1Encodable i  : as) {
					 ASN1Integer ii = (ASN1Integer)i;
					byte[] s =  ii.getEncoded("DER");
					
					byte[] e = get(s);
					rst.add(e);
				 }
				 return rst;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		static byte[] get(byte[] data) {
			int length = data[1];
			byte[] rst = new byte[length];
			for(int i = 0; i < length; i++)
				rst[i] = data[i + 2];
			return rst;
		}
		static public void main(String[] ss) {
			String sig = "MEcCICMXZTSiCrw0osEtV7pXtgGTqRJEay931VOpjqI3hbOyAiAyEHmqIVyxT6qnE3dO/3n3RkQ4dUCgFVRC0RpdGd0G5wIBHA==";
			List<byte[]> sigrsv = decodeSignature(sig);
			System.out.print(sigrsv);
			
		}

}
