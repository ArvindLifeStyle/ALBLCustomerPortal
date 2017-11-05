package com.arvind.customerPortal.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.api.controller.UserApiController;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.mapper.LoginRequestToDtoMapper;
import com.arvind.customerPortal.model.LoginRequest;
//import com.arvind.customerPortal.model.LoginRequest.RoleEnum;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.InternalRegisterService;
import com.arvind.customerPortal.service.LoginService;
import com.arvind.customerPortal.service.impl.ExternalRegisterServiceImpl;

/*enum RoleEnum {
	ADMIN("ADMIN"),

	CUSTOMER("CUSTOMER"),

	ARVIND_BRAND_TEAM("ARVIND BRAND TEAM");

	@NotNull
	private String value;

	RoleEnum(String value) {
		this.value = value;

	}*/

@RunWith(MockitoJUnitRunner.class)
public class UserApiControllerTest {
	
	@InjectMocks
	UserApiController userApiController;
	
/*	 @Mock
	 public RoleEnum testAdmin = RoleEnum.ADMIN;*/

	@Mock
	private InternalRegisterService internalRegisterService;

	@Mock
	private ExternalRegisterServiceImpl externalRegisterServiceImpl;

	@Mock
	private TokenParser testtokenParser;
	
	@Mock
	private BindingResult result;
	
	/*@Mock
	private RoleEnum roleEnum;*/

	@Mock
	private Validator testValidator;
	
	@Mock
	private LoginRequestToDtoMapper testMapper;

	@Mock
	private RequestValidatation requestValidatation;
	
	@Mock
	private LoginService testLoginService;
	
	
	/*test request and response objects*/
	//private LoginRequest testLoginRequestCorrect=new LoginRequest();
	private LoginResult testLoginResult=new LoginResult();
	private UserRegister testUserRegister=new UserRegister();
	LoginRequest testLoginRequestCorrect=new LoginRequest();
	//Pre-Initialize the mock objects
	@Before
	public void setUp()
	{

		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(userApiController, "mapper", testMapper);
		ReflectionTestUtils.setField(userApiController, "requestValidatation", requestValidatation);
		ReflectionTestUtils.setField(userApiController, "loginservice", testLoginService);
		ReflectionTestUtils.setField(userApiController, "tokenParser", testtokenParser);
		ReflectionTestUtils.setField(userApiController, "validator", testValidator);
		ReflectionTestUtils.setField(userApiController, "externalRegisterService", externalRegisterServiceImpl);
		//ReflectionTestUtils.setField(testLoginRequestCorrect, "RoleEnum", testAdmin);
	}
	
	/*@Test
	public void testLogin() throws UserNotFoundException
	{
		testLoginRequestCorrect.setPassword("test");
		//testLoginRequestCorrect.setRole(testAdmin);
		testLoginRequestCorrect.setUsername("testUser");
		userApiController.doLogin(testLoginRequestCorrect,result);
		LoginRequestDTO testLoginDto=new LoginRequestDTO();
		testLoginDto.setId(123);
		testLoginDto.setPassword("test");
		testLoginDto.setRole("roleEnum.ADMIN");
		testLoginDto.setUsername("testUser");
		testLoginResult.setOk("ok");
		Mockito.doNothing().when(requestValidatation).validateLoginRequest(testLoginRequestCorrect);
		Mockito.when(testMapper.mapLogintoDto(testLoginRequestCorrect)).thenReturn(testLoginDto);
		Mockito.when(testLoginService.getLoginDetails(testLoginDto)).thenReturn(testLoginResult);
	}*/
	
	/*@Test
	public void externalRegistration() throws InsufficientAuthoritiesException
	{
		testUserRegister.setEmail("testemail");
		testUserRegister.setName("testName");
		testUserRegister.setPassword("testPw");
		testUserRegister.setRole("CUSTOMER");
		Mockito.doNothing().when(requestValidatation).validateUserRegisterRequest(testUserRegister);
		Mockito.doNothing().when(requestValidatation).isExist(testUserRegister);
		userApiController.externalRegistration(testUserRegister, errors, "test");
		//Mockito.doReturn(true).when(testtokenParser.getRole("test"));
	}
	*/
	
	@Test
	public void test() {
		System.out.println("Junit Testing");
	}

}

