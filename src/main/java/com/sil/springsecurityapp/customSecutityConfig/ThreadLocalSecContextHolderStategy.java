package com.sil.springsecurityapp.customSecutityConfig;

public class ThreadLocalSecContextHolderStategy implements SecContextHolderStrategy{


    @Override
    public Void clearContext() {
        return null;
    }

    @Override
    public SecContext getContext() {
        return null;
    }

    @Override
    public void setContext(SecContext context) {

    }
}
