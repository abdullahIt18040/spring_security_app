package com.sil.springsecurityapp.customSecutityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//SecContextHolderFilter = seccurity contextholdrFilter
public class SecContextHolderFilter extends OncePerRequestFilter {
//      SecurityContextHolderStrategy

    //fetch user auth infromation from session and  keep into storage ,sothat we can access or get data from controller

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
  filterChain.doFilter(request,response);
    }
}
