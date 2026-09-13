package com.sil.springsecurityapp.customSecutityConfig;

public class UserNotFoundException extends AuthException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
