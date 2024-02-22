package com.acmlab.file.config.shrio.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码工具类
 *
 * @program: NovelClient
 * @author: lixin
 * @create: 2021-05-31 15:58
 **/
public class PasswordUtils {
    public static String HashMD5(String username, String password) {
        ByteSource salt = ByteSource.Util.bytes(username);
        String result = new SimpleHash("MD5", password, salt, 1024).toHex();
        return result;
    }

}
