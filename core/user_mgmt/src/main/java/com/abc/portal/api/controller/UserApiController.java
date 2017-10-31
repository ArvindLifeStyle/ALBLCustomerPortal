package com.abc.portal.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abc.portal.models.UserRegister;
import com.abc.portal.repo.entity.UserInfo;
import com.abc.portal.service.UserService;
import com.abc.portal.validation.Validatation;

@Service
public class UserApiController {

	@Autowired
	private UserService registrationService;

	@Autowired
	private Validatation validatation;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserInfo internalUserRegister(final UserRegister userRegister) {

		logger.info("adminRegister method in UserResourceHelper ");

		validatation.validateUserAdminDetails(userRegister);
		userRegister.setPassword(getSecuredPassword(userRegister.getPassword()));
		return registrationService.internalUserRegister(userRegister);

	}

	public UserInfo externalUserRegister(final UserRegister userRegister) {

		logger.info("adminRegister method in UserResourceHelper ");

		validatation.validateUserAdminDetails(userRegister);
		userRegister.setPassword(getSecuredPassword(userRegister.getPassword()));
		return registrationService.externalUserRegister(userRegister);

	}

	private String getSecuredPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static HttpServletRequest getCurrentRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		Assert.state(requestAttributes != null, "Could not find current request via RequestContextHolder");
		Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
		HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
		return servletRequest;
	}

}
