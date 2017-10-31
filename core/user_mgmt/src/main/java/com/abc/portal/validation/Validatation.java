package com.abc.portal.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.abc.portal.exception.DataNotFoundException;
import com.abc.portal.models.UserRegister;

@Service
public class Validatation {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean validateUserAdminDetails(UserRegister userRegister) {
		logger.info("validateUserAdminDetails method in Validation class");

		if (userRegister != null) {
			if (userRegister.getName() == null && userRegister.getName().isEmpty()) {
				throw new DataNotFoundException("Need name");
			} else if (userRegister.getEmail() == null && userRegister.getEmail().isEmpty()) {
				throw new DataNotFoundException("Need email");
			} else if (userRegister.getPassword() != null && userRegister.getPassword().isEmpty()) {
				throw new DataNotFoundException("Need email");
			}
			return true;
		} else {
			throw new DataNotFoundException("Required userCustomerInfo");
		}
	}

	public boolean validateUserCustomerDetails(UserRegister userCustomerInfo) {
		logger.info("validateUserAdminDetails method in Validation class");
		if (userCustomerInfo != null) {
			if (userCustomerInfo.getName().isEmpty()) {
				throw new DataNotFoundException("Need name");
			} else if (userCustomerInfo.getEmail().isEmpty()) {
				throw new DataNotFoundException("Need email");
			} else if (userCustomerInfo.getPassword().isEmpty()) {
				throw new DataNotFoundException("Need email");
			}
			return true;
		} else {
			throw new DataNotFoundException("Required userCustomerInfo");
		}

	}

}
