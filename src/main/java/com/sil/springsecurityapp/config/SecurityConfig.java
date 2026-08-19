package com.sil.springsecurityapp.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultFilterChain() {
        return new SecurityFilterChain() {
            @Override
            public boolean matches(HttpServletRequest request) {

                return request.getRequestURI().startsWith("/api");
            }

            @Override
            public List<Filter> getFilters() {
                return List.of(new ApiKeyFilter());
            }
        };
    }

    static class ApiKeyFilter extends OncePerRequestFilter {
        private static final String API_KEY = "SIMLE_API_KEY";

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            String apiKey = request.getHeader("X-API-KEY");
//            if (apiKey == null) {
//                filterChain.doFilter(request,response);
//                return;
//            }
             if(API_KEY.equals(apiKey))
             {
            filterChain.doFilter(request,response);
             }else {
                 response.setStatus(HttpStatus.FORBIDDEN.value());
                 response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                 response.getWriter().println("""
                         {
                         "error message ": "hey bro! you are not permited to perform this operation ",
                          "STATUS ":404,
                           "ERROR ":"FORBIDDEN"
                         }
                         
                         
                         """);
             }


        }



    }
    static class simpleFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

//            System.out.println("this is my first filter in sdlc pro ");
//            response.getWriter().println("hellow bro ! ");
            filterChain.doFilter(request, response);

        }
    }
}

