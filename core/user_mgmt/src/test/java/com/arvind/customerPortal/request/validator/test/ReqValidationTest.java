package com.arvind.customerPortal.request.validator.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.model.Userstore;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class ReqValidationTest {
	
	@InjectMocks
	RequestValidatation requestValidatation;
	
	@Mock
	LoginService loginservice;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void validateLoginRequest_Test() {
		
		LoginRequest loginRequest =new LoginRequest();
		loginRequest.setUsername("abc");
		loginRequest.setPassword("password");
		loginRequest.setRole(LoginRequest.RoleEnum.ADMIN);		
		requestValidatation.validateLoginRequest(loginRequest);
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is email");
		LoginRequest loginRequest1 =new LoginRequest();
		loginRequest1.setPassword("password");
		loginRequest1.setRole(LoginRequest.RoleEnum.ADMIN);
		
		requestValidatation.validateLoginRequest(loginRequest1);
	}
	
	@Test
	public void validateEmptyUserLoginRequest_Test() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is email");
		LoginRequest loginRequest2 =new LoginRequest();
		loginRequest2.setUsername("");
		loginRequest2.setPassword("password");
		loginRequest2.setRole(LoginRequest.RoleEnum.ADMIN);

		requestValidatation.validateLoginRequest(loginRequest2);
	}
	
	@Test
	public void validatePasswordLoginRequest_Test() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is password");
		LoginRequest loginRequest =new LoginRequest();
		loginRequest.setUsername("abc");		
		loginRequest.setRole(LoginRequest.RoleEnum.ADMIN);
		
		requestValidatation.validateLoginRequest(loginRequest);
		
	}
	
	@Test
	public void validateEmptyPasswordLoginRequest_Test() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is password");
		LoginRequest loginRequest =new LoginRequest();
		loginRequest.setUsername("abc");
		loginRequest.setPassword("");
		loginRequest.setRole(LoginRequest.RoleEnum.ADMIN);		
		requestValidatation.validateLoginRequest(loginRequest);
	}
	
	
	@Test
	public void validateRoleLoginRequest_Test() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is role");
		LoginRequest loginRequest =new LoginRequest();
		loginRequest.setUsername("abc");	
		loginRequest.setPassword("password");		
		
		requestValidatation.validateLoginRequest(loginRequest);
		
		
	}
	
	@Test
	public void validateUserRegisterRequestTest(){
		UserRegister userRegister = new UserRegister();
		userRegister.setName("name");
		userRegister.setEmail("email");
		userRegister.setPassword("password");
		userRegister.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister);
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is name");
		
		UserRegister userRegister1 = new UserRegister();
		userRegister1.setEmail("email");
		userRegister1.setPassword("password");
		
		userRegister1.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister1);	
		
	}
	
	
	@Test
	public void validateEmptyNameUserRegisterRequestTest(){
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is name");
		UserRegister userRegister = new UserRegister();
		userRegister.setName("");
		userRegister.setEmail("email");
		userRegister.setPassword("password");
		userRegister.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister);
		
	}
	
	@Test
	public void validateUserRegisterRequestEmailTest(){
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is email");
		UserRegister userRegister2 = new UserRegister();
		userRegister2.setName("name");
		userRegister2.setPassword("password");
		userRegister2.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister2);
		
	}
	
	@Test
	public void validateEmptyEmailUserRegisterRequestTest(){
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is email");
		UserRegister userRegister = new UserRegister();
		userRegister.setName("name");
		userRegister.setEmail("");
		userRegister.setPassword("password");
		userRegister.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister);
	}
	
	@Test
	public void validateUserRegisterRequestPasswordTest(){
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is password");
		UserRegister userRegister3 = new UserRegister();
		userRegister3.setName("name");
		userRegister3.setEmail("email");
		userRegister3.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister3);
		
	}
	
	@Test
	public void validateEmptyPasswordUserRegisterRequestTest(){
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is password");
		UserRegister userRegister = new UserRegister();
		userRegister.setName("name");
		userRegister.setEmail("email");
		userRegister.setPassword("");
		userRegister.setRole("ADMIN");
		requestValidatation.validateUserRegisterRequest(userRegister);
	}
	
	@Test
	public void validateUserRegisterRequestRoleTest(){
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is role");
		UserRegister userRegister4 = new UserRegister();
		userRegister4.setName("name");
		userRegister4.setEmail("email");
		userRegister4.setPassword("password");
		requestValidatation.validateUserRegisterRequest(userRegister4);
		
	}
	
	@Test
	public void validatestoreRequestTest() {
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		store.setStoreid("storeid");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is name");
		
		Request req1 = new Request();
		Store store1 = new Store();		
		store1.setAddress("address");
		store1.setStoreid("storeid");
		Phone p1 = new Phone();
		p1.setCc("cc");
		p1.setNumber("number");
		store1.setPhone(p1);
		req1.setStore(store1);
		requestValidatation.validatestoreRequest(req1);
		
		
	}
	
	@Test
	public void validateEmptyNameStoreRequestTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is name");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("");
		store.setAddress("address");
		store.setStoreid("storeid");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validatestoreRequestAddressTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is address");
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setStoreid("storeid");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	
	@Test
	public void validateEmptyAddressStoreRequestTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is address");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("");
		store.setStoreid("storeid");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validatestoreRequestStoreIDTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is storeid");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validateEmptyStoreIdStoreRequestTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is storeid");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		store.setStoreid("");
		Phone p = new Phone();
		p.setCc("cc");
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	
	@Test
	public void validatestoreRequestPhoneTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is phonenumber and countrycode");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		store.setStoreid("storeid");
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validateNullPhoneNoStoreRequestTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is phonenumber and countrycode");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		store.setStoreid("storeid");
		Phone p = new Phone();
		p.setCc("cc");		
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	
	@Test
	public void validateNullPhoneCcStoreRequestTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is phonenumber and countrycode");
		
		Request req = new Request();
		Store store = new Store();
		store.setName("name");
		store.setAddress("address");
		store.setStoreid("storeid");
		Phone p = new Phone();		
		p.setNumber("number");
		store.setPhone(p);
		req.setStore(store);
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validatestoreRequestStoreTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is store");
		
		Request req = new Request();
		requestValidatation.validatestoreRequest(req);
		
	}
	
	@Test
	public void validateUserStoreRequestTest() {
		RequestUserstore req = new RequestUserstore();
		Userstore userstore = new Userstore();
		userstore.setStoreid("storeid");
		userstore.setUserid(1);
		req.setUserstore(userstore);
		requestValidatation.validateUserStoreRequest(req);
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is storeid");
		
		RequestUserstore req1 = new RequestUserstore();
		Userstore userstore1 = new Userstore();
		userstore1.setUserid(1);
		req1.setUserstore(userstore1);
		requestValidatation.validateUserStoreRequest(req1);
		
	}
	
	@Test
	public void validateEmptyStoreUserStoreRequestTest() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is storeid");
		RequestUserstore req = new RequestUserstore();
		Userstore userstore = new Userstore();
		userstore.setStoreid("");
		userstore.setUserid(1);
		req.setUserstore(userstore);
		requestValidatation.validateUserStoreRequest(req);
		
	}
	
	
	@Test
	public void validateUserStoreRequestUserIdTest() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is userid");
		
		RequestUserstore req = new RequestUserstore();
		Userstore userstore = new Userstore();
		userstore.setStoreid("storeid");
		req.setUserstore(userstore);
		requestValidatation.validateUserStoreRequest(req);
	}
	
	@Test
	public void validatestoreRequestUserstoreTest() {
		
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("Mandatory field is userstore");
		
		RequestUserstore req = new RequestUserstore();
		requestValidatation.validateUserStoreRequest(req);
		
	}

	@Test
	public void isUserExistTest() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("User already exists");
		
		List<BusUser> listBus = new ArrayList<BusUser>();
		BusUser bu = new BusUser();
		bu.setName("busname");
		listBus.add(bu);
		
		when(loginservice.getUserDetails("busname")).thenReturn(listBus);
		UserRegister userRegister = new UserRegister();
		userRegister.setName("busname");
		requestValidatation.isExist(userRegister);
	}
	
	@Test
	public void isUserExistEmail() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("User already exists");
		List<BusUser> listBus1 = new ArrayList<BusUser>();
		BusUser bu1 = new BusUser();
		bu1.setName("busname1");
		bu1.setEmail("email");
		listBus1.add(bu1);
		
		when(loginservice.getUserDetails(any(String.class))).thenReturn(listBus1);
		UserRegister userRegister1 = new UserRegister();
		userRegister1.setName("busname2");
		userRegister1.setEmail("email");		
		requestValidatation.isExist(userRegister1);
	}
	
	
	@Test
	public void isUserExistPassword() {
		expectedEx.expect(DataNotFoundException.class);
		expectedEx.expectMessage("User already exists");
		List<BusUser> listBus2 = new ArrayList<BusUser>();
		BusUser bu2 = new BusUser();
		bu2.setName("busname1");
		bu2.setEmail("email1");
		bu2.setPassword("password");
		listBus2.add(bu2);
		
		when(loginservice.getUserDetails(any(String.class))).thenReturn(listBus2);
		UserRegister userRegister2 = new UserRegister();
		userRegister2.setName("busname2");
		userRegister2.setEmail("email2");
		userRegister2.setPassword("password");
		requestValidatation.isExist(userRegister2);
	}
	
	@Test
	public void isUserExistNativeTest() {
		List<BusUser> listBus = new ArrayList<BusUser>();
		BusUser bu = new BusUser();
		bu.setName("busname");
		bu.setEmail("email");
		bu.setPassword("password");
		listBus.add(bu);
		
		when(loginservice.getUserDetails(any(String.class))).thenReturn(listBus);
		UserRegister userRegister = new UserRegister();
		userRegister.setName("busname1");
		userRegister.setEmail("email1");
		userRegister.setPassword("password1");
		requestValidatation.isExist(userRegister);
	
	}
	
	
	
	
}
