package com.arvind.customerPortal.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.model.SuccessResult;
import com.arvind.customerPortal.model.UserRegister;


public interface UserApi {

	@PostMapping(value = "/user/login", produces = { "application/json" }, consumes = {"application/json" })
	ResponseEntity<LoginResult> doLogin(@Valid @RequestBody LoginRequest loginRequest)
			throws UserNotFoundException;

	@PostMapping(value = "/user/enterprise/external/register", produces = { "application/json" }, consumes = {"application/json" })
	ResponseEntity<SuccessResult> externalRegistration(@Valid @RequestBody UserRegister user,@RequestHeader(value = "security-token", required = true) String securityToken)
			throws InsufficientAuthoritiesException;

	@PostMapping(value = "/user/enterprise/internal/register", produces = { "application/json" }, consumes = {"application/json" })
	ResponseEntity<SuccessResult> internalRegistration(@Valid @RequestBody UserRegister user);

}
