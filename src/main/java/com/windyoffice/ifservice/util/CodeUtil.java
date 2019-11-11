package com.windyoffice.ifservice.util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * Base64编码工具类
 * 
 * @author windyoffice
 *
 */
public class CodeUtil {

	/** 换行 */
	private static final String NEW_LINE = "\n";

	/** 字符集 */
	private static final String CHAR_SET = "UTF-8";

	/**
	 * 编码
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		try {
			return new BASE64Encoder().encode(str.getBytes(CHAR_SET)).replace(NEW_LINE, "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/***
	 *
	 * @param bytes
	 * @return
	 */
	public static String encode(byte[] bytes) {
		try {
			return new BASE64Encoder().encode(bytes).replace(NEW_LINE, "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		try {
			return new String(new BASE64Decoder().decodeBuffer(str));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}