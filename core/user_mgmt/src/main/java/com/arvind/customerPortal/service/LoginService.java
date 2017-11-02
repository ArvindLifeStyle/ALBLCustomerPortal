package com.arvind.customerPortal.service;

import org.springframework.stereotype.Service;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;




public interface LoginService {
	
	public  LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException;

}
