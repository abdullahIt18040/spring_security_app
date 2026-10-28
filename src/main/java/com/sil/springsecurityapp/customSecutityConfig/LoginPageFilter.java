package com.sil.springsecurityapp.customSecutityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginPageFilter extends OncePerRequestFilter {

 private  static  final  String defaultLoginPageUrl = "/login";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (this.isLoginPageRequest(request))
        {
            String  loginPareHTML = generateLoginPageHTML();
            response.setContentType(MediaType.TEXT_HTML_VALUE);
            response.setContentLength(loginPareHTML.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(loginPareHTML);
            return;

        }

                filterChain.doFilter(request,response);
    }
    private String generateLoginPageHTML()
    {
        return """
                <!DOCTYPE html>
                                               <html lang="en">
                                               <head>
                                                   <title>Bootstrap Example</title>
                                                   <meta charset="utf-8">
                                                   <meta name="viewport" content="width=device-width, initial-scale=1">
                                                   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                                                   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                                                   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
                                               </head>
                                               <body>
                
                                               <div class="container">
                                                   <h2>Vertical (basic) form</h2>
                                                   <form action="/login" method="post">
                                                       <div class="form-group">
                                                           <label for="email">UserName</label>
                                                           <input type="text" class="form-control" id="email" placeholder="Enter email" name="username">
                                                       </div>
                                                       <div class="form-group">
                                                           <label for="pwd">Password:</label>
                                                           <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
                                                       </div>
                                                       <div class="checkbox">
                                                           <label><input type="checkbox" name="remember"> Remember me</label>
                                                       </div>
                                                       <button type="submit" class="btn btn-default">Submit</button>
                                                   </form>
                                               </div>
                
                                               </body>
                                               </html>
                
                """;
    }

    private boolean isLoginPageRequest(HttpServletRequest request)
    {
        if(!"GET".equals(request.getMethod()))
        {
            return false;
        }
        String uri = request.getRequestURI();

       return uri.equals(request.getContextPath()+defaultLoginPageUrl);

    }
}
