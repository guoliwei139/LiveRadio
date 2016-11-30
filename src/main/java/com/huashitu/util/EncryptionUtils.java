package com.huashitu.util;

import com.huashitu.util.sign.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayOutputStream;
import java.security.Key;

/**
 * Security 提供了一个安全算法类,对称密码算法
 */
public final class EncryptionUtils {
	private static final Logger LOG = Logger.getLogger(EncryptionUtils.class);
	// The length of Encryptionstring should be 8 bytes and not be
	// a weak key
	private final static Base64 base64encoder = new Base64();
	// private final static BASE64Decoder base64decoder = new BASE64Decoder();
	private final static String encoding = "utf-8";
	// 向量  
    private final static String iv = "83792805"; 
    //key
    private final static String key = "mengzhup";

    public EncryptionUtils() {
        System.out.println("ffffffffffffff");
    }

    /**
	 * 加密字符串
	 */
	public static String encryptString(String str) {
		String sKey = key;
		String result = str;
		//原字符串不为空
		if (StringUtils.isNotBlank(str)) {
			try {
				//对称加密
				byte[] encodeByte = symmetricEncrypt(str.getBytes(encoding), sKey);
				synchronized (base64encoder) {
					//base64编码
					result = base64encoder.encode(encodeByte);
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());;
				return "-1";
			}

			result = result.replace("+", "%2B");
		}
		return result;
	}

	/**
	 * 解密字符串
	 */
	public static String decryptString(String str) {
		String sKey = key;
		String result = str;
		//加密字符串不为空
		if (StringUtils.isNotBlank(str)) {
			try {
				byte[] encodeByte = null;
				synchronized (base64encoder) {
					//base64解码
					encodeByte = base64encoder.decode(str);
				}
				if(encodeByte != null) {
					//对称加密
					byte[] decoder = EncryptionUtils.symmetricDecrypto(encodeByte, sKey);
					//字节数组转换成字符串
					result = new String(decoder, encoding);
				}
			} catch (Exception e) {//异常
				LOG.error(e.getMessage());;
			}
		}
		return result;
	}

	/**
	 * 对称加密方法
	 * 
	 * @param byteSource
	 *            需要加密的数据
	 * @return 经过加密的数据
	 * @throws Exception
	 */
	public static byte[] symmetricEncrypt(byte[] byteSource, String sKey) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int mode = Cipher.ENCRYPT_MODE;
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			byte[] keyData = sKey.getBytes();
			DESKeySpec  keySpec = new DESKeySpec(keyData);
			Key key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
			cipher.init(mode, key,ips);
			byte[] result = cipher.doFinal(byteSource);

			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			baos.close();
		}
	}

	/**
	 * 对称解密方法
	 * 
	 * @param byteSource
	 *            需要解密的数据
	 * @return 经过解密的数据
	 * @throws Exception
	 */
	public static byte[] symmetricDecrypto(byte[] byteSource, String sKey) throws Exception {
		int mode = Cipher.DECRYPT_MODE;
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		byte[] keyData = sKey.getBytes();
		DESKeySpec  keySpec = new DESKeySpec (keyData);
		Key key = keyFactory.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
		cipher.init(mode, key,ips);
		byte[] result = cipher.doFinal(byteSource);
		return result;
	}
}
