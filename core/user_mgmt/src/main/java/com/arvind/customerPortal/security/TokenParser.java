package com.arvind.customerPortal.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenParser {
	
	private String secret="secret";
	public String getRole(String Token)
	{
		String role=null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(Token)
                    .getBody();

            

            
            role=(String) body.get("role");
        }
        catch (Exception e) {
            System.out.println(e);
        }
		return role;
	}

}
