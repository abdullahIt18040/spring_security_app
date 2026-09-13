package com.sil.springsecurityapp.customSecutityConfig;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthManager {
//    UserDetailsService
   // DaoAuthenticationProvider in hood used userDetailsServices
    // In this case we are used authmanager as DaoAuthenticationProvider;
    private final SecUserDetailService userDetailService = new SecUserDetailService();
 public Auth authenticated(Auth auth)
 {
      var userDetails =userDetailService.loadUserByUsername(auth.username());
      return new Auth(userDetails.username(),null,userDetails.authorities(),true);
 }
}
