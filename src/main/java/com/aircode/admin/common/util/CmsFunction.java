package com.aircode.admin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.aircode.admin.common.exception.CmsException;

public class CmsFunction {
	
	
	public static String aesEncode(String text) throws UnsupportedEncodingException, CmsException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		 
		return AES256Util.aesEncode(text);
	}
	
	public static String aesDecode(String encText) throws UnsupportedEncodingException, CmsException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		 
		return AES256Util.aesDecode(encText);
	}
	
	public static String getFileName(String fileName) {
		return fileName.substring(fileName.lastIndexOf("/") + 1);
	}
	
	public static String transImg(String path,String cid) {
		String imgPath = "";
		imgPath = "/transimg?path=/"+path+"&cid="+cid;
		return imgPath;
	}
	
	public static long byteTo(long bytes , String target) {
		
		long KB = 1024;
		long MB = KB * 1024;
		long GB = MB * 1024;
		long result =0;
		switch(target) {
		case "KB" : 
			result = bytes / KB;
			break;
		case "MB" : 
			result = bytes / MB;
			break;
		case "GB" : 
			result = bytes / GB;
			break;
		}
		return result;
	}
}
