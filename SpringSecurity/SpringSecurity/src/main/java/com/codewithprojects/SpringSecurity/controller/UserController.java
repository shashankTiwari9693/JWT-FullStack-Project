package com.codewithprojects.SpringSecurity.controller;

import com.codewithprojects.SpringSecurity.dto.UserHelloResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public UserHelloResponse hello(){
        return new UserHelloResponse("Hello From JWT Authorization :- USER");
    }
}
