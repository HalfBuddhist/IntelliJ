package com.lqw.common;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class StringCoder {
	
	private static Logger logger = Logger.getLogger(StringCoder.class);

	
	/**
	 * Encode String with MD5 hash method,
	 * only for ASC2 letters.
	 * 
	 * @param _p_str_source
	 * @return
	 */
	public static String encodeWithMD5(String _p_str_source) {
		StringBuffer sb = new StringBuffer(32);

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(_p_str_source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			logger.error("Can not encode the string '" + _p_str_source + "' to MD5!", e);
			return null;
		}

		return sb.toString();
	}
	
	
	/**
	 * BKDR Hash 
	 * 
	 * @param _p_str_source the source string
	 * @return the unsigned int hash value
	 */
	public static int encodeWithBKDRHash(String _p_str_source)
	{
		int seed = 131; // 31 131 1313 13131 131313 etc..
		int hash = 0, _l_i_index = 0;
	 
		while (_l_i_index < _p_str_source.length()){
			hash = hash * seed + _p_str_source.charAt(_l_i_index++);
		}
	 
		return (hash & 0x7FFFFFFF);
	}
	
	
	/**
	 * test main function 
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(StringCoder.encodeWithMD5("abc"));
		System.out.println(StringCoder.encodeWithBKDRHash("2B348BA431457B25B3C5A1ADBDD06989.mp3")%1000);
	}
}
