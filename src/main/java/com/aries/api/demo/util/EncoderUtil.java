package com.aries.api.demo.util;

import com.github.pagehelper.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @Author: AriesHoo on 2019/2/12 13:40
 * @E-Mail: AriesHoo@126.com
 * @Function: 加密相关
 * @Description:
 */
public class EncoderUtil {

    /**
     * 利用MD5进行加密
     *
     * @param str
     * @return
     */
    public static String encoderByMd5(String str) {
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newStr = null;
        try {
            newStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return StringUtil.isEmpty(newStr) ? str : newStr;
    }

}
