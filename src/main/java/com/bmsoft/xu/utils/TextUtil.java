package com.bmsoft.xu.utils;

/**
 * Created by 81954 on 2017/11/20.
 */
public class TextUtil {
    public static Boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }
        return false;
    }

    public static String ApendString(String url,String child){
        StringBuffer sb = new StringBuffer().append(url);
        sb.append(child);
        return sb.toString();
    }


}
