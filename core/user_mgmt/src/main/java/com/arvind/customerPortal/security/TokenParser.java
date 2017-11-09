package com.arvind.customerPortal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenParser {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String secret="secret";
	public String getRole(String token)
	{
		String role=null;
		try {
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			role=(String) body.get("role");
		}
		catch (Exception exception) {
			logger.info(exception.getMessage());
		}
		return role;
	}

}
