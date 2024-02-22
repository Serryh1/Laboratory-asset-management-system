package com.acmlab.file.utils;

import java.io.UnsupportedEncodingException;

/**
 * URL转码工具类
 *
 * @program: NovelClient
 * @description: URL转码工具类
 * @author: lixin
 * @create: 2021-06-24 16:43
 **/
public class URLutil {
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
