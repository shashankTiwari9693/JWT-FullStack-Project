package com.codewithprojects.SpringSecurity.service.impl;

import com.codewithprojects.SpringSecurity.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {




public String  generateToken(UserDetails userDetails){
    return Jwts.builder().setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis())).
            setExpiration(new Date(System.currentTimeMillis() +1000 *60*24)) //valid for 1 day
            .signWith(getSigninKey(), SignatureAlgorithm.HS256)
            .compact();
}

    public String  generateRefreshToken(Map<String, Object>extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() +604800000))  //valid for 7 days
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }



public String extractUserName(String token){
    return extractClaim(token,Claims::getSubject);
}

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

private Key getSigninKey(){
    byte[] key= Decoders.BASE64.decode("413F4428472B4B62506553685660597033733676397924422645294840406351");
    return Keys.hmacShaKeyFor(key);
}
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
    final String username = extractUserName(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
    return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
