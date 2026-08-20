package com.sil.springsecurityapp;



import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecurityAppApplication {

    public static void main(String[] args) {



        SpringApplication.run(SpringSecurityAppApplication.class, args);

        System.out.println("This is spring security applicairon ");


    }

}
