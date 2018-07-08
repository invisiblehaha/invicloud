package cn.vision.invicloud.support.common;

import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtils {
	
	/** 迭代次数 */
	private static final int ITERATIONS = 5;
	
	private PasswordUtils() {

	}
	
	/**
	 * 字符串加密函数MD5实现
	 * @param password 密码
	 * @param salt 盐值
	 * @return
	 */
	public static String getMd5(String password, String salt) {
		return new Md5Hash(password, salt, ITERATIONS).toString();
	}


	/** 获得密码盐值 */
	public static String getSalt() {
		return String.valueOf(RandomUtils.nextInt(100000,999999));
	}
}
