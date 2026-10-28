package com.sil.springsecurityapp.customSecutityConfig;

public class SecContext {
    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    private  Auth auth;

}
