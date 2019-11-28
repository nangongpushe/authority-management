package com.mmall.demo;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by jimin on 2017/8/26.
 */
public class MyPasswordEncoder implements PasswordEncoder {

    private final static String SALT = "123456";
    /**
     * 加密方法
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        SALT相当于参考值
        return encoder.encodePassword(rawPassword.toString(), SALT);
    }

    /**
     * 匹配方法：拿原始的密码和加密后的密码进行匹配
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.isPasswordValid(encodedPassword, rawPassword.toString(), SALT);
    }
}
