package com.windyoffice.ifservice.util;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/***
 * 
 * @author windyoffice
 *
 */
public class RSAKeyGenerator {

	private static Logger logger = LoggerFactory.getLogger(RSAKeyGenerator.class);
	
    /**
     *
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static void main(String[] args) {
        try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); //创建‘密匙对’生成器
			kpg.initialize(512); //指定密匙长度（取值范围：512～2048）
			KeyPair kp = kpg.genKeyPair(); //生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
			PublicKey public_key = kp.getPublic(); //获得公匙
			PrivateKey private_key = kp.getPrivate(); //获得私匙
			// 输出公匙
			String publicKey = CodeUtil.encode(public_key.getEncoded());
			// 输出私匙
			String privateKey = CodeUtil.encode(private_key.getEncoded());

			logger.info(publicKey);
			logger.info(privateKey);
			
		} catch (Exception e) {
			logger.error("",e);
		}

    }
}
