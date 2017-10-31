package com.abc.portal.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.portal.api.controller.UserApiController;
import com.abc.portal.models.UserRegister;
import com.abc.portal.repo.entity.UserInfo;

@RestController
@RequestMapping("/v1/user")
public class UserApi {

	@Autowired
	private UserApiController userApiController;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = ("/enterprise/internal/register"))
	@ResponseBody
	public ResponseEntity<?> internalUserRegister(@Valid @RequestBody UserRegister userRegister, BindingResult errors) {

		logger.info("internalUserRegister method in UserResource ");

		UserInfo userAdminInfo = userApiController.internalUserRegister(userRegister);
		return new ResponseEntity<>(userAdminInfo, HttpStatus.OK);
	}

	@PostMapping(value = ("/enterprise/external/register"))
	@ResponseBody
	public ResponseEntity<?> externalUserRegister(@RequestBody @Valid final UserRegister userRegister,
			final BindingResult errors) {

		logger.info("internalUserRegister method in UserResource ");

		UserInfo userAdminInfo = userApiController.externalUserRegister(userRegister);
		return new ResponseEntity<>(userAdminInfo, HttpStatus.OK);

	}
}
