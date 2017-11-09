package com.arvind.customerPortal.security.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.security.TokenGenerator;
import com.arvind.customerPortal.security.TokenParser;

@RunWith(MockitoJUnitRunner.class)
public class TokenTest {
	
	@InjectMocks
	TokenGenerator tokenGenerator;
	
	@InjectMocks
	TokenParser tokenParser;
	
	@Test
	public void tokenGeneratorandParserTest() {		
		LoginRequestDTO loginDTO =new LoginRequestDTO();
		loginDTO.setUsername("username");
		loginDTO.setPassword("password");
		loginDTO.setRole("role");
		String token = tokenGenerator.generate(loginDTO, "secret");
		String str = tokenParser.getRole(token);
		assertNotNull(token);
		assertNotNull(str);
		assertEquals("role", str);
		
		tokenParser.getRole(null);
		
	}
}
