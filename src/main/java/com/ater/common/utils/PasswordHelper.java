package com.ater.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class PasswordHelper {
	
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	private static String algorithmName = "md5";
	
	private static final int hashIterations = 1;
	
	public static String encryptPassword(String plainPwd, boolean withSalt, String algorithmName, int hashIterations) {
		if(withSalt) {
			return new SimpleHash(algorithmName, plainPwd, 
						ByteSource.Util.bytes(randomNumberGenerator.nextBytes().toHex()), hashIterations).toHex();
		} else {
			return new SimpleHash(algorithmName, plainPwd).toHex();
		}
	}
	
	public static String encryptPassword(String plainPwd, boolean withSalt) {
		if(withSalt) {
			return new SimpleHash(algorithmName, plainPwd, 
						ByteSource.Util.bytes(randomNumberGenerator.nextBytes().toHex()), hashIterations).toHex();
		} else {
			return new SimpleHash(algorithmName, plainPwd).toHex();
		}
	}
	
	public static String encryptPassword(String plainPwd) {
		return new SimpleHash(algorithmName, plainPwd).toHex();
	}
	
	public static String encryptPassword(String plainPwd, String algorithmName) {
		return new SimpleHash(algorithmName, plainPwd).toHex();
	}
	
	public static void main(String[] args) {
		System.out.println(PasswordHelper.encryptPassword("123456"));	
		System.out.println(PasswordHelper.encryptPassword("123456"));	
	}
}