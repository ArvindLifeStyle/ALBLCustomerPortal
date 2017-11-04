package com.arvind.customerPortal.Dao;

import java.util.List;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

@Repository
@PropertySource("classpath:application.properties")
public interface UserDao {

	LoginResult getLoginDetails(LoginRequestDTO loginDTO) throws UserNotFoundException;
	
	List<BusUser> getUserDetails(String uname);
	
	public Integer getUserByName(String uname);
}
