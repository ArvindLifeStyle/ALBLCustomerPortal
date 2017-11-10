package com.arvind.customerPortal.request.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.model.Userstore;
import com.arvind.customerPortal.service.LoginService;

@Service
public class RequestValidatation {

	private static final String MANDATORY_FIELD_EMAIL = "Mandatory field is email";
	private static final String MANDATORY_FIELD_NAME = "Mandatory field is name";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginservice;

	public void validateLoginRequest(LoginRequest loginRequest) {

		logger.info("At validateLoginRequest method in RequestValidatation");
		
		if (null == loginRequest.getUsername() || loginRequest.getUsername().isEmpty()) {
			logger.error(MANDATORY_FIELD_EMAIL);
			throw new DataNotFoundException(MANDATORY_FIELD_EMAIL);
		} else if (null == loginRequest.getPassword() || loginRequest.getPassword().isEmpty()) {
			logger.error("Mandatory field is password");
			throw new DataNotFoundException("Mandatory field is password");
		} else if (null == loginRequest.getRole()) {
			logger.error("Mandatory field is role");
			throw new DataNotFoundException("Mandatory field is role");
		}

	/*	if (loginRequest.getRole().equals("ADMIN") || loginRequest.getRole().equals("CUSTOMER")) {

		} else {

			logger.error("Required valid Role ");
			throw new DataNotFoundException("Required valid Role");

		}*/

	}
	
	public void validateUserRegisterRequest(UserRegister userRegister) {
		
		logger.info("At validateUserRegisterRequest method in RequestValidatation");

		if (null == userRegister.getName() || userRegister.getName().isEmpty()) {
			logger.error("MANDATORY_FIELD_NAME");
			throw new DataNotFoundException("MANDATORY_FIELD_NAME");
		} else if (null == userRegister.getEmail() || userRegister.getEmail().isEmpty()) {
				logger.error("MANDATORY_FIELD_EMAIL");
				throw new DataNotFoundException("MANDATORY_FIELD_EMAIL");
		} else if (null == userRegister.getPassword() || userRegister.getPassword().isEmpty()) {
			logger.error("Mandatory field is password");
			throw new DataNotFoundException("Mandatory field is password");
		} else if (null == userRegister.getRole()) {
			logger.error("Mandatory field is role");
			throw new DataNotFoundException("Mandatory field is role");
		}
		
	/*	if(userRegister.getRole().equals("ADMIN") || userRegister.getRole().equals("CUSTOMER")) {
			
		}else {
			
			logger.error("Required valid Role ");
			throw new DataNotFoundException("Required valid Role");
			
		}*/
	}
	
	
	public void  isExist(UserRegister userRegister){
		
		logger.info("At isExist method in RequestValidatation");
		
		List<BusUser> list = loginservice.getUserDetails(userRegister.getName());
		
		for(BusUser b : list) {
			if(b.getName().equals(userRegister.getName()) || b.getEmail().equals(userRegister.getEmail()) || b.getPassword().equals(userRegister.getPassword()))
				throw new DataNotFoundException("User already exists");
		}
		
	}
	
	
	public void validatestoreRequest(Request request) {
		
		logger.info("At validatestoreRequest method in RequestValidatation");
		
		Store store = request.getStore();

		if (null == store) {
			logger.error("Mandatory field is store");
			throw new DataNotFoundException("Mandatory field is store");
		} else {
			if (null == store.getName() || store.getName().isEmpty()) {
				logger.error(MANDATORY_FIELD_NAME);
				throw new DataNotFoundException(MANDATORY_FIELD_NAME);
			} else if (null == store.getAddress() || store.getAddress().isEmpty()) {
				logger.error("Mandatory field is address");
				throw new DataNotFoundException("Mandatory field is address");
			} else if (null == store.getStoreid() || store.getStoreid().isEmpty()) {
				logger.error("Mandatory field is storeid");
				throw new DataNotFoundException("Mandatory field is storeid");
			} else if (null == store.getPhone() || null == store.getPhone().getNumber()
					|| null == store.getPhone().getCc()) {
				logger.error("Mandatory field is role");
				throw new DataNotFoundException("Mandatory field is phonenumber and countrycode");
			}
		}

	}
	
	public void validateUserStoreRequest(RequestUserstore request) {
		
		logger.info("At validateUserStoreRequest method in RequestValidatation");
		
		Userstore store = request.getUserstore();

		if (null == store) {
			logger.error("Mandatory field is userstore");
			throw new DataNotFoundException("Mandatory field is userstore");
		} else {
			if (null == store.getStoreid() || store.getStoreid().isEmpty()) {
				logger.error("Mandatory field is storeid");
				throw new DataNotFoundException("Mandatory field is storeid");
			} else if (store.getUserid() <= 0) {
				logger.error("Mandatory field is userid");
				throw new DataNotFoundException("Mandatory field is userid");
			}
		}
	}
}
