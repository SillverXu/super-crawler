package com.bmsoft.xu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class MD5Encoding {
    private static Logger logger =Logger.getLogger("MD5Encoding.class");

    public static String StringToMD5(String str)  {
        String newStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            newStr = bytesToHex(md5.digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            logger.info("MD5加密算法错误"+e.toString());
        } catch (UnsupportedEncodingException e) {
            logger.info("传入的不是UTF-8格式的字符串"+e.toString());
        }
        return newStr;
    }
    private static String bytesToHex(byte[] bytes){
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0){
                num += 256;
            }
            if(num<16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toLowerCase();
    }
}
