package com.arvind.customerPortal.Dao;

import java.util.List;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

public interface UserDao {

	LoginResult getLoginDetails(LoginRequestDTO loginDTO) throws UserNotFoundException;
	
	List<BusUser> getUserDetails(String uname);
}
