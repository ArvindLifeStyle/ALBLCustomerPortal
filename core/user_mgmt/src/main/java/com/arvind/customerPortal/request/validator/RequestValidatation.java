package com.arvind.customerPortal.request.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.UserRegister;

@Service
public class RequestValidatation {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void validateLoginRequest(LoginRequest loginRequest) {

		if (null == loginRequest.getUsername() || loginRequest.getUsername().isEmpty()) {
			logger.error("Mandatory field is email");
			throw new DataNotFoundException("Mandatory field is email");
		} else if (null == loginRequest.getPassword() || loginRequest.getPassword().isEmpty()) {
			logger.error("Mandatory field is password");
			throw new DataNotFoundException("Mandatory field is password");
		} else if (null == loginRequest.getRole()) {
			logger.error("Mandatory field is role");
			throw new DataNotFoundException("Mandatory field is role");
		}

	}
	
	public void validateUserRegisterRequest(UserRegister userRegister) {

		if (null == userRegister.getName() || userRegister.getName().isEmpty()) {
			logger.error("Mandatory field is name");
			throw new DataNotFoundException("Mandatory field is name");
		} else if (null == userRegister.getEmail() || userRegister.getEmail().isEmpty()) {
				logger.error("Mandatory field is email");
				throw new DataNotFoundException("Mandatory field is email");
		} else if (null == userRegister.getPassword() || userRegister.getPassword().isEmpty()) {
			logger.error("Mandatory field is password");
			throw new DataNotFoundException("Mandatory field is password");
		} else if (null == userRegister.getRole()) {
			logger.error("Mandatory field is role");
			throw new DataNotFoundException("Mandatory field is role");
		}
	}
	

}
