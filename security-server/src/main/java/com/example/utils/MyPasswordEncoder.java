package com.example.utils;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder  implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        Md4PasswordEncoder encoder = new Md4PasswordEncoder();
        return encoder.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence);
    }
}
