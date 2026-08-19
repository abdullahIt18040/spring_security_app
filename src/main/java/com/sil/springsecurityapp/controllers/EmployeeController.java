package com.sil.springsecurityapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @GetMapping("/public")
    public ResponseEntity<?> info()
    {
        return ResponseEntity.ok("i am employee contelller ");
    }
}
