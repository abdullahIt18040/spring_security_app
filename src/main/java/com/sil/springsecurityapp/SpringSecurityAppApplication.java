package com.sil.springsecurityapp;



import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.DelegatingFilterProxy;


@SpringBootApplication
public class SpringSecurityAppApplication {

    public static void main(String[] args) {
//    ApplicationFilterChain
//        SecurityFilterChain;
//     DelegatingFilterProxy


        SpringApplication.run(SpringSecurityAppApplication.class, args);

        System.out.println("This is spring security applicairon ");


    }

}
