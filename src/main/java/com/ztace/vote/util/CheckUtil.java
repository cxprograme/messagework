package com.ztace.vote.util;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;


/**
 * 校验类
 * @author ChenXu
 *
 */
public class CheckUtil {
	private static final transient Log log = LogFactory.getLog(CheckUtil.class);
	private static  String token;
	public static boolean check(String signature,String timestamp,String nonce){
		token=AppInfoUtil.getToken();
		log.debug("token:"+token);
		String arr[]=new String[]{token,timestamp,nonce};
		//对三个参数进行排序
		Arrays.sort(arr);
		
		//将三个参数拼接成字符串
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		//进行sha1加密
		String temp=getSha1(content.toString());
		return temp.equals(signature);
	}
	
	public static String getSha1(String str){
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
		
	} 
}
