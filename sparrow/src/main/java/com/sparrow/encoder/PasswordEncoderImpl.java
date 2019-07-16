package com.sparrow.encoder;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(String password) {
        return password;
    }
}
