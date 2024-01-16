package com.codewithprojects.SpringSecurity.service;

import com.codewithprojects.SpringSecurity.dto.JwtAuthenticationResponse;
import com.codewithprojects.SpringSecurity.dto.RefreshTokenRequest;
import com.codewithprojects.SpringSecurity.dto.SignUpRequest;
import com.codewithprojects.SpringSecurity.dto.SigninRequest;
import com.codewithprojects.SpringSecurity.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
