package com.sil.springsecurityapp.customSecutityConfig;

import java.util.List;

public record Auth (String username,
                    String password,
                    List<String> authorities,
                    boolean isAuthenticated){
}
