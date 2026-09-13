package com.sil.springsecurityapp.customSecutityConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionBaseAuthentication {
    public void onAuthenticate(Auth auth, HttpServletRequest request,
                               HttpServletResponse response)
    {
    var sesion = request.getSession();
    sesion.setAttribute("SPRING-SECURITY-USER",auth);
//        SecurityContextHolder

    }
}
