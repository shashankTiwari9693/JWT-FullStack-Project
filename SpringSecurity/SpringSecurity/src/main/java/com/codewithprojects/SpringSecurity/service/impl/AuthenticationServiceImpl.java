package com.codewithprojects.SpringSecurity.service.impl;

import com.codewithprojects.SpringSecurity.dto.JwtAuthenticationResponse;
import com.codewithprojects.SpringSecurity.dto.RefreshTokenRequest;
import com.codewithprojects.SpringSecurity.dto.SignUpRequest;
import com.codewithprojects.SpringSecurity.dto.SigninRequest;
import com.codewithprojects.SpringSecurity.entities.Role;
import com.codewithprojects.SpringSecurity.entities.User;
import com.codewithprojects.SpringSecurity.repository.UserRepository;
import com.codewithprojects.SpringSecurity.service.AuthenticationService;
import com.codewithprojects.SpringSecurity.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public User signup(SignUpRequest signUpRequest){
        User user=new  User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setSecondname(signUpRequest.getLastname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));

        var user=userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));

        var jwt=jwtService.generateToken(user);

        var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;


    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail=jwtService.extractUserName(refreshTokenRequest.getToken());
        User user=userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){

            var jwt=jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setRefreshToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;

        }
   return null;
    }
}
