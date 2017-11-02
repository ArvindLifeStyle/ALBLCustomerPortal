package com.arvind.customerPortal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.arvind.customerPortal.Dto.LoginRequestDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {

	
    public String generate(LoginRequestDTO loginDTO, String secret) {
        Claims claims = Jwts.claims().setSubject(loginDTO.getUsername());
        claims.put("userId", loginDTO.getUsername());
        claims.put("role", loginDTO.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
	
}
    }
