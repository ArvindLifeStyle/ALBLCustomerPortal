package com.arvind.customerPortal.service;

import java.util.List;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

public interface LoginService {

	public LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException;
	
	List<BusUser> getUserDetails(String uname);

}
