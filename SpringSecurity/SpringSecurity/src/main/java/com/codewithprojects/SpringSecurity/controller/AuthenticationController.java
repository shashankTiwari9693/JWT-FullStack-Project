package com.codewithprojects.SpringSecurity.controller;

import com.codewithprojects.SpringSecurity.dto.JwtAuthenticationResponse;
import com.codewithprojects.SpringSecurity.dto.RefreshTokenRequest;
import com.codewithprojects.SpringSecurity.dto.SignUpRequest;
import com.codewithprojects.SpringSecurity.dto.SigninRequest;
import com.codewithprojects.SpringSecurity.entities.User;
import com.codewithprojects.SpringSecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User>signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public  ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public  ResponseEntity<JwtAuthenticationResponse>refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
