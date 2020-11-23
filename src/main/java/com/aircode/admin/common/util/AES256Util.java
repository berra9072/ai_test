package com.aircode.admin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.aircode.admin.common.exception.CmsException;

public class AES256Util {
	
	private static String iv;
	private static Key keySpec;
	
	//private static String key = "@irc0d2_CmS_@2s!";
	private static String key = "shoppingntshopping";
	
	public AES256Util() throws UnsupportedEncodingException, CmsException {
		try {
			iv = key.substring(0, 16);
	
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			int len = b.length;
			if(len > keyBytes.length) {
				len = keyBytes.length;
			}
			
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	
			keySpec = secretKeySpec;
		} catch(Exception e) {
			throw new CmsException("aes256 set error", "암복호화 모듈 설정 오류");
		}
	}

	/**
	 * 
	 * <pre>
	 * 1.개요     : 암호화
	 * 2.처리내용 : 입력 정보를 암호화
	 * </pre>
	 *
	 * @Method Name : aesEncode
	 * @param str
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String aesEncode(String str) throws java.io.UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));

		return enStr;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요     : 복호화
	 * 2.처리내용 : 입력 정보를 복호화
	 * </pre>
	 *
	 * @Method Name : aesDecode
	 * @param str
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String aesDecode(String str) throws java.io.UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
		String returnString = null;
		
		if(str != null) {
			byte[] byteStr = Base64.decodeBase64(str.getBytes());
			returnString = new String(c.doFinal(byteStr), "UTF-8");
		}
		
		return returnString;
	}
}
