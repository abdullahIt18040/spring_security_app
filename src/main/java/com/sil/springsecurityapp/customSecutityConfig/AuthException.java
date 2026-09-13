package com.sil.springsecurityapp.customSecutityConfig;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
