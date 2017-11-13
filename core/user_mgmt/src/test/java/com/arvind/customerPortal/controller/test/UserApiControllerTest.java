package com.arvind.customerPortal.controller.test;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.api.controller.UserApiController;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.mapper.LoginRequestToDtoMapper;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.InternalRegisterService;
import com.arvind.customerPortal.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class UserApiControllerTest {

	@InjectMocks
	UserApiController userApiController;


	@Mock
	private InternalRegisterService internalRegisterService;

	@Mock
	private ExternalRegisterService externalRegisterService123;

	@Mock
	private TokenParser testtokenParser;

	@Mock
	private BindingResult result;

	@Mock
	private Validator testValidator;

	@Mock
	private LoginRequestToDtoMapper testMapper;

	@Mock
	private RequestValidatation requestValidatation;

	@Mock
	private LoginService testLoginService;


	/*test request and response objects*/
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
	}

	@Test
	public void testLogin() throws UserNotFoundException
	{
		testLoginRequestCorrect.setPassword("test");
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
	}

	@Test
	public void exRegistration() throws InsufficientAuthoritiesException {		
		BindingResult errors = null;
		testUserRegister.setEmail("testemail");
		testUserRegister.setName("testName");
		testUserRegister.setPassword("testPw");
		testUserRegister.setRole("CUSTOMER");
		Mockito.doNothing().when(requestValidatation).validateUserRegisterRequest(testUserRegister);
		Mockito.doNothing().when(requestValidatation).isExist(testUserRegister);
		when(testtokenParser.getRole("token")).thenReturn("ADMIN");
		when(testValidator.validate("ADMIN", "register_user")).thenReturn(true);
		when(externalRegisterService123.register(testUserRegister)).thenReturn(true);
		userApiController.externalRegistration(testUserRegister, errors, "token");

		testUserRegister = null;
		when(externalRegisterService123.register(testUserRegister)).thenReturn(false);
		userApiController.externalRegistration(testUserRegister, errors, "token");

	}


	@Test
	public void internalRegistration() {

		BindingResult errors = null;
		testUserRegister.setEmail("testemail");
		testUserRegister.setName("testName");
		testUserRegister.setPassword("testPw");
		testUserRegister.setRole("CUSTOMER");
		Mockito.doNothing().when(requestValidatation).validateUserRegisterRequest(testUserRegister);
		Mockito.doNothing().when(requestValidatation).isExist(testUserRegister);			
		Mockito.when(internalRegisterService.register(testUserRegister)).thenReturn(true);
		userApiController.internalRegistration(testUserRegister, errors);

		testUserRegister = null;

		Mockito.when(internalRegisterService.register(testUserRegister)).thenReturn(false);
		userApiController.internalRegistration(testUserRegister, errors);
	}

}

