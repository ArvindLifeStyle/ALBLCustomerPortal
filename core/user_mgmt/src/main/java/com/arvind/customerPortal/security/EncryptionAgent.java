package com.arvind.customerPortal.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionAgent {
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	public String encode(String password){
	    return encoder.encode(password);
	}

}
