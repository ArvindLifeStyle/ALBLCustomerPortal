package com.arvind.customerPortal.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.api.UserApi;
import com.arvind.customerPortal.constants.AuthsConstants;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.mapper.LoginRequestToDtoMapper;
import com.arvind.customerPortal.model.FailureResult;
import com.arvind.customerPortal.model.Https;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.model.SuccessResult;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.InternalRegisterService;
import com.arvind.customerPortal.service.LoginService;

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

	@Autowired
	private RequestValidatation requestValidatation;

	LoginRequestToDtoMapper mapper = new LoginRequestToDtoMapper();
	LoginRequestDTO loginDto = new LoginRequestDTO();
	LoginResult loginResult;

	public ResponseEntity<LoginResult> doLogin(@Valid @RequestBody LoginRequest loginRequest, BindingResult errors) throws UserNotFoundException{
		requestValidatation.validateLoginRequest(loginRequest);
		loginDto = mapper.mapLogintoDto(loginRequest);
		loginResult = loginservice.getLoginDetails(loginDto);
		return new ResponseEntity<LoginResult>(loginResult, HttpStatus.OK);
	}

	public ResponseEntity<?> externalRegistration(@Valid @RequestBody UserRegister userRegister, BindingResult errors,
			@RequestHeader(value = "Authorization", required = true) String securityToken)
			throws InsufficientAuthoritiesException {
		boolean persistanceFlag;
		
		requestValidatation.validateUserRegisterRequest(userRegister);
		requestValidatation.isExist(userRegister);
		
		String role = tokenParser.getRole(securityToken);
		boolean flag = validator.validate(role, AuthsConstants.USER_REGISTRATION);
		if (flag) {
			persistanceFlag = externalRegisterService.register(userRegister);
			if (persistanceFlag) {
				SuccessResult result = new SuccessResult();
				Https http$ = new Https();
				http$.setStatus((HttpStatus.OK).toString());
				result.setOk("true");
				result.setHttp$(http$);
				result.setWhy("successful registration");
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				FailureResult failureResult = new FailureResult();
				Https http$ = new Https();
				http$.setStatus((HttpStatus.BAD_REQUEST).toString());
				failureResult.setOk("false");
				failureResult.setHttp$(http$);
				failureResult.setWhy("failure registration");
				return new ResponseEntity<>(failureResult, HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new InsufficientAuthoritiesException();
		}

	}

	public ResponseEntity<?> internalRegistration(@Valid @RequestBody UserRegister userRegister, BindingResult errors) {
		
		requestValidatation.validateUserRegisterRequest(userRegister);		
		requestValidatation.isExist(userRegister);		
		
		boolean flag = internalRegisterService.register(userRegister);
		if (flag) {
			SuccessResult result = new SuccessResult();
			Https http$ = new Https();
			http$.setStatus((HttpStatus.OK).toString());
			result.setOk("true");
			result.setHttp$(http$);
			result.setWhy("successful registration");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			FailureResult failureResult = new FailureResult();
			Https http$ = new Https();
			http$.setStatus((HttpStatus.BAD_REQUEST).toString());
			failureResult.setOk("false");
			failureResult.setHttp$(http$);
			failureResult.setWhy("failure registration");
			return new ResponseEntity<>(failureResult, HttpStatus.BAD_REQUEST);
		}
	}

}
