package com.arvind.customerPortal.service;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

public interface LoginService {

	public LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException;

}
