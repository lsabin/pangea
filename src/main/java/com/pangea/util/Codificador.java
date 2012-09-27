package com.pangea.util;

import org.apache.commons.codec.binary.Hex;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Codificador {

	static String algoritmo = "SHA-1";
	static String codificacion = "UTF-8";
	
	public static String codifica(String clave) throws NoSuchAlgorithmException , UnsupportedEncodingException {
	    MessageDigest digest = MessageDigest.getInstance(algoritmo);
	    digest.update(clave.getBytes(codificacion));
	    byte[] raw = digest.digest();
	    return new String(Hex.encodeHex(raw));
	
	}
}

