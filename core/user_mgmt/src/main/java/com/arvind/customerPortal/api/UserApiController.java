package com.arvind.customerPortal.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.mapper.LoginRequestToDtoMapper;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.model.SuccessResult;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.InternalRegisterService;
import com.arvind.customerPortal.service.LoginService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-30T11:47:53.319Z")

@Controller
public class UserApiController implements UserApi {

	@Autowired
	private LoginService loginservice;

	@Autowired
	private InternalRegisterService internalRegisterService;

	@Autowired
	private ExternalRegisterService externalRegisterService;

	@Autowired
	private TokenParser tokenParser;

	@Autowired
	private Validator validator;

	LoginRequestToDtoMapper mapper = new LoginRequestToDtoMapper();
	LoginRequestDTO loginDto = new LoginRequestDTO();
	LoginResult loginResult;

	public ResponseEntity<LoginResult> doLogin(
			@ApiParam(value = "This object contains the email id and password for the incoming customer.", required = true) @Valid @RequestBody LoginRequest loginRequest)
			throws UserNotFoundException {
		loginDto = mapper.mapLogintoDto(loginRequest);
		loginResult = loginservice.getLoginDetails(loginDto);
		return new ResponseEntity<LoginResult>(loginResult, HttpStatus.OK);
	}

	public ResponseEntity<SuccessResult> externalRegistration(
			@ApiParam(value = "This object contains the email id and password for the new customer.", required = true) @Valid @RequestBody UserRegister user,
			@ApiParam(value = "User Identification Token", required = true) @RequestHeader(value = "security-token", required = true) String securityToken)
			throws InsufficientAuthoritiesException {
		boolean flag = false;
		boolean persistanceFlag;
		String role = tokenParser.getRole(securityToken);
		flag = validator.validate(role);
		if (flag == true) {
			SuccessResult result = new SuccessResult();
			persistanceFlag = externalRegisterService.register(user);
			if (persistanceFlag == true) {
				result.setMessage("success");
			} else {
				result.setMessage("failure");
			}
		} else {
			throw new InsufficientAuthoritiesException();
		}

		return new ResponseEntity<SuccessResult>(HttpStatus.OK);
	}

	public ResponseEntity<SuccessResult> internalRegistration(
			@ApiParam(value = "This API onboards enterprises internal user.", required = true) @Valid @RequestBody UserRegister user) {
		boolean flag;
		SuccessResult result = new SuccessResult();
		flag = internalRegisterService.register(user);
		if (flag == true) {
			result.setMessage("success");
		}

		else {
			result.setMessage("failure");
		}
		return new ResponseEntity<SuccessResult>(result, HttpStatus.OK);
	}

}
