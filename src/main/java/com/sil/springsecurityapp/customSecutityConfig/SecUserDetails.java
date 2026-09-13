package com.sil.springsecurityapp.customSecutityConfig;

import java.util.List;

public record SecUserDetails(String username,
                             String password,
                             List<String>authorities) {
}
