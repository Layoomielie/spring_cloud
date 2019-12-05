package com.example.utils;

import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    //md5 加密
    MessageDigestPasswordEncoder md5 = new MessageDigestPasswordEncoder("MD5");
    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    //官方推荐使用加密算法
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }

    public static void main(String[] args) {
        String encode = new MyPasswordEncoder().encode("123456");
        System.out.println(encode);
    }
}
