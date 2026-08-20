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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SecurityConfig {
    private final RateLimitFilter rateLimitFilter = new RateLimitFilter();
    @Bean
    public SecurityFilterChain defaultFilterChain() {
        return new SecurityFilterChain() {
            @Override
            public boolean matches(HttpServletRequest request) {

                return request.getRequestURI().startsWith("/api");
            }

            @Override
            public List<Filter> getFilters() {
                return List.of(new ApiKeyFilter(),
                        new simpleFilter(),
                        rateLimitFilter);
            }
        };
    }

    static class ApiKeyFilter extends OncePerRequestFilter {
        private static final String API_KEY = "SIMLE_API_KEY";

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            String apiKey = request.getHeader("X-API-KEYPRO");
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
    static class RateLimitFilter extends OncePerRequestFilter{
          private static final int MaxRequest  = 5;
        private final Map<String, Integer> requestCounter = new ConcurrentHashMap<>();

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            String clientIp = request.getRemoteAddr();

          int count=  requestCounter.merge(clientIp,1,Integer::sum);

            System.out.println(
                    "RateLimitFilter: "
                            + clientIp
                            + " -> "
                            + count
            );

            if(count>MaxRequest)
            {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().println("""
                    {
                        "status": 429,
                        "error": "TOO_MANY_REQUESTS",
                        "message": "Too many requests. Please try again later."
                    }
                    """);
                return;
            }
            filterChain.doFilter(request,response);



        }
    }
}

