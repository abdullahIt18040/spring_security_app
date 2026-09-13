package com.sil.springsecurityapp.customSecutityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserNamePasswordFilter extends OncePerRequestFilter {

   private AntPathMatcher matcher = new AntPathMatcher();
   private AuthManager authManager = new AuthManager();
   private final SessionBaseAuthentication  sessionBaseAuthentication= new SessionBaseAuthentication();
 private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //AuthenticationManager
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String path = request.getRequestURI();
     String method = request.getMethod();
     if (!(matcher.match("/login", path) && HttpMethod.POST.name().equals(method)))
     {
      filterChain.doFilter(request,response);
        return;
     }

     String username= request.getParameter("username");
     String password =request.getParameter("password");

       try {
        var auth= authManager.authenticated(new Auth(username,password,
                   null,false));
        sessionBaseAuthentication.onAuthenticate(auth,request,response);
        successfullyAuthenticate(request,response);

       }catch (AuthException authException)
       {
           unsuccessfullyAuthenticate(request,response);

       }



//   filterChain.doFilter(request,response);
    }

    private void successfullyAuthenticate(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request,response,"/");



    }
    private void unsuccessfullyAuthenticate(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request,response,"/login?error");
    }
}
