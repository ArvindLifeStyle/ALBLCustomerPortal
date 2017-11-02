package com.arvind.customerPortal.Dao;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

public interface UserDao {

	LoginResult getLoginDetails(LoginRequestDTO loginDTO) throws UserNotFoundException;
}
