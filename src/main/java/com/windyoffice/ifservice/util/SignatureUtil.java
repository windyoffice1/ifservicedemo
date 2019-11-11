package com.windyoffice.ifservice.util;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * 签名工具类
 * 
 * @author windyoffice
 *
 */
public class SignatureUtil {

	/** 换行 */
	private static final String NEW_LINE = "\n";

	/** 算法 */
	private static final String ALGORITHM = "SHA256WITHRSA";

	/**
	 * 加签
	 * 
	 * @param text
	 *            待加签文本
	 * @param privateKey
	 *            加签私钥
	 * @return
	 */
	public static String sign(String text, String privateKey) {
		try {
			byte[] signBytes = sign(CodeUtil.encode(text).getBytes(), CodeUtil.decode(privateKey).getBytes("utf-8"), ALGORITHM);
			return CodeUtil.encode(signBytes).replace(NEW_LINE, "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param text
	 * @param privateKeyData
	 * @param algorithm
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static byte[] sign(final byte[] text, final byte[] privateKeyData, final String algorithm) throws GeneralSecurityException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyData);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		Signature signatureChecker = Signature.getInstance(algorithm);
		signatureChecker.initSign(privateKey);
		signatureChecker.update(text);
		return signatureChecker.sign();
	}

	/**
	 * 验签
	 * 
	 * @param text
	 *            待验签字符串
	 * @param signedText
	 *            加签串
	 * @param publicKey
	 *            验签公钥
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static boolean verify(String text, String signedText, String publicKey) {
		try {
			return verify(CodeUtil.encode(text).getBytes(), CodeUtil.decode(signedText).getBytes("utf-8"), CodeUtil.decode(publicKey).getBytes("utf-8"), ALGORITHM);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param text
	 * @param signedText
	 * @param publicKeyData
	 * @param algorithm
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static boolean verify(final byte[] text, final byte[] signedText, final byte[] publicKeyData, final String algorithm) throws GeneralSecurityException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyData);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		Signature signatureChecker = Signature.getInstance(algorithm);
		signatureChecker.initVerify(publicKey);
		signatureChecker.update(text);
		return signatureChecker.verify(signedText);
	}

	public static String oneLine(String body) {
		return body.replaceAll(">\\s+<", "><");
	}


	public static void main(String[] args) {
		String signData=SignatureUtil.sign("张三",PropertiesUtil.getProperty("sign.privatekey"));
		System.out.println(signData);
	}

}
