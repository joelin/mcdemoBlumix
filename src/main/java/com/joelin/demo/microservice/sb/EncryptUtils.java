package com.joelin.demo.microservice.sb;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.StringUtils;


public class EncryptUtils {


	public static String encryptMD5(String msg){
		return encrypt(msg, null);
	}
	

	private static String encrypt(String msg, String type){
		MessageDigest md;
		StringBuilder password = new StringBuilder();
		
		try {
			md = MessageDigest.getInstance("MD5");
			
			if(StringUtils.isNoneBlank(type)){
				md.update(type.getBytes());
			}else {
				md.update(msg.getBytes());
			}
			
			byte[] bytes = md.digest();
			for (int i = 0; i < bytes.length; i++) {
				String param = Integer.toString((bytes[i] & 0xff) + 0x100, 16);
				password.append(param.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		

		return password.toString();
	}
	

	public static String encryptSalt(String msg) {
		String salt = getSalt();
		return encrypt(msg, salt);
	}
	
	
	public static String encryptSHA(String msg) {
		String salt = getSaltSHA1();
		StringBuilder sb = new StringBuilder();
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(msg.getBytes());
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
		}catch(Exception e){
			
		}
		
		return sb.toString();
	}
	

	public static String encryptPBKDF2(String msg) {
		try {
			int iterations = 1000;
			char[] chars = msg.toCharArray();
			byte[] salt = getSalt().getBytes();
			 
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			
			return iterations + toHex(salt) + toHex(hash);
		} catch (Exception e) {
			e.printStackTrace();
		} 
       
        return null;
	}
	

	 private static String toHex(byte[] array) {
	        BigInteger bi = new BigInteger(1, array);
	        String hex = bi.toString(16);
	        int paddingLength = (array.length * 2) - hex.length();
	        if(paddingLength > 0) {
	            return String.format("%0"  +paddingLength + "d", 0) + hex;
	        }else{
	            return hex;
	        }
	    }
	

    private static String getSaltSHA1(){
        SecureRandom sr;
        byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			 sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
       
        return salt.toString();
    }
    
	

	private static String getSalt(){
		SecureRandom sr;
		byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			sr.nextBytes(salt);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return salt.toString();
	}

}
