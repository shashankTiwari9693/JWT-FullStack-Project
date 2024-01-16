package com.codewithprojects.SpringSecurity.controller;

import com.codewithprojects.SpringSecurity.dto.AdminHelloResponse;
import com.codewithprojects.SpringSecurity.dto.UserHelloResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @GetMapping
    public AdminHelloResponse hello(){
        return new AdminHelloResponse("Hello From JWT Authorization :- ADMIN");
    }
}
