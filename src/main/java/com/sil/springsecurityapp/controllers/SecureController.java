package com.sil.springsecurityapp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class SecureController  {
    @GetMapping("/secure")
    public ResponseEntity<?>getSecureData()
    {
        return ResponseEntity.ok("get secure data");
    }
}
