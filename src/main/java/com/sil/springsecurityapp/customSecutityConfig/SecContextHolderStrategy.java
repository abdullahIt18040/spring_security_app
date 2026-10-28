package com.sil.springsecurityapp.customSecutityConfig;

import org.springframework.security.core.context.SecurityContext;

import java.util.function.Supplier;

public interface SecContextHolderStrategy {
    Void clearContext();

    SecContext getContext();



    void setContext(SecContext context);


}
