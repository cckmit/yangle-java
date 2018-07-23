package com.ater.common.utils;

public class RandomUtil {

	
	/**
	 * 生成六位随机密码
	 * @return
	 */
	public static String getRandomPassword(){
		double random = Math.random();
		String randomPassword = Double.toString(random).substring(2, 8);
		return randomPassword;
	}

	public static void main(String[] args) {

		getRandomPassword();
	}

}
