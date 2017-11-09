package com.arvind.customerPortal.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arvind.customerPortal.api.controller.StoreApiController;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesRuntimeException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.ResponseMultiple;
import com.arvind.customerPortal.model.Status;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.Userstore;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.IStoreService;
import com.arvind.customerPortal.service.impl.StoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StoreApiControllerTest {
	
	@InjectMocks
	StoreApiController storeApiController;

	@Mock
	private StoreServiceImpl storeServiceImpl;
	
	@Mock
	private IStoreService iStoreService;
	
	@Mock
	private RequestValidatation requestValidatation;
	
	@Mock
	private LoginRequest loginRequest;
	
	@Mock
	private TokenParser tokenParser;
	
	@Mock
	private Validator validator;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void test_create_store1() throws Exception {
		String st = "ADMIN";
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setAddress("address");
		storeEntity.setStoreid("storeid");
		storeEntity.setName("abc");
		PhoneEntity pe2 = new PhoneEntity();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		storeEntity.setPhone(pe2);
		when(tokenParser.getRole("token")).thenReturn(st);
		when(validator.validate("ADMIN", "create_store")).thenReturn(true);
		when(iStoreService.createStore(any(StoreEntity.class))).thenReturn(storeEntity);
		
		ResponseEntity responseEntity = new ResponseEntity(getSuccessStatus(), HttpStatus.OK);
		assertTrue(responseEntity.equals(storeApiController.createStore(getStoreRequest(),"token")));
		
		expectedEx.expect(InsufficientAuthoritiesRuntimeException.class);
		storeApiController.createStore(getStoreRequest(),"token1");
		
	}
	
	@Test
	public void test_exception_create_store() throws Exception {
		String str = "ADMIN";
		StoreEntity storeEntity = null;
		when(tokenParser.getRole("token")).thenReturn(str);
		when(validator.validate("ADMIN", "create_store")).thenReturn(true);
		when(iStoreService.createStore(storeEntity)).thenReturn(storeEntity);
		
		ResponseEntity responseEntity = new ResponseEntity(getFailureStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(responseEntity,storeApiController.createStore(getStoreRequest(),"token"));
		
	}
	
	@Test
	public void test_search_store() {
		List<Store> ls = new ArrayList<Store>();
		Store st = new Store();
		st.setName("str");
		st.setAddress("TS");
		st.setStoreid("storeid");
		Phone pe2 = new Phone();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		st.setPhone(pe2);
		ls.add(st);
		when(tokenParser.getRole("token")).thenReturn("ADMIN");
		when(validator.validate("ADMIN", "search_store")).thenReturn(true);
		when(iStoreService.searchStore("str")).thenReturn(ls);
		
		ResponseEntity expectedResponseEntity = new ResponseEntity(getSearchResponse(), HttpStatus.OK);
		ResponseEntity<?> actualResponseEntity = storeApiController.searchStore("str","token");
		
		assertEquals(expectedResponseEntity, actualResponseEntity);
		
	}
	
	@Test
	public void test_exception_search_store() throws Exception {
		List<Store> ls = null;
		String str = "ADMIN";
		when(tokenParser.getRole("token")).thenReturn(str);
		when(validator.validate("ADMIN", "search_store")).thenReturn(true);
		when(iStoreService.searchStore("str")).thenReturn(ls);
		
		ResponseEntity responseEntity = new ResponseEntity(getFailureStatus(), HttpStatus.BAD_REQUEST);
		ResponseEntity<?> actualResponseEntity = storeApiController.searchStore("str","token");
		
		assertEquals(responseEntity, actualResponseEntity);
		
	}
	
	@Test
	public void test_retrieve_store() {
		List<Store> ls = new ArrayList<Store>();
		Store st = new Store();
		st.setName("str");
		st.setAddress("TS");
		st.setStoreid("storeid");
		Phone pe2 = new Phone();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		st.setPhone(pe2);
		ls.add(st);
		when(tokenParser.getRole("token")).thenReturn("ADMIN");
		when(validator.validate("ADMIN", "retrieve_store")).thenReturn(true);
		when(iStoreService.retrieveStore("str")).thenReturn(ls);
		storeApiController.retrieveStore("str","token");
	}
	
	@Test
	public void test_exception_retrieve_store() throws Exception {
		List<Store> ls = null;
		String str = "ADMIN";
		when(tokenParser.getRole("token")).thenReturn(str);
		when(validator.validate("ADMIN", "retrieve_store")).thenReturn(true);
		when(iStoreService.retrieveStore("str")).thenReturn(ls);
		
		ResponseEntity responseEntity = new ResponseEntity(getFailureStatus(), HttpStatus.BAD_REQUEST);
		ResponseEntity<?> actualResponseEntity = storeApiController.retrieveStore("str","token");
		
		assertEquals(responseEntity, actualResponseEntity);
		
	}
	
	@Test
	public void test_retrieve_userstore() {
		List<Store> ls = new ArrayList<Store>();
		Store st = new Store();
		st.setName("s1");
		st.setAddress("TS");
		st.setStoreid("storeid");
		Phone pe2 = new Phone();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		st.setPhone(pe2);
		ls.add(st);
		when(tokenParser.getRole("token")).thenReturn("ADMIN");
		when(validator.validate("ADMIN", "retrive_userstore")).thenReturn(true);
		when(iStoreService.getUserByName("username")).thenReturn(1);
		when(iStoreService.getStoreId(1)).thenReturn("s1");
		when(iStoreService.retrieveStore("s1")).thenReturn(ls);
		storeApiController.retriveUserstore("username","token");
	}
	
	@Test
	public void test_exception_retrieve_userstore() throws Exception {
		List<Store> ls = null;
		String str = "ADMIN";
		when(tokenParser.getRole("token")).thenReturn("ADMIN");
		when(validator.validate("ADMIN", "retrive_userstore")).thenReturn(true);
		when(iStoreService.getUserByName("username")).thenReturn(1);
		when(iStoreService.getStoreId(1)).thenReturn("s1");
		when(iStoreService.retrieveStore("s1")).thenReturn(ls);
		
		ResponseEntity responseEntity = new ResponseEntity(getFailureStatus(), HttpStatus.BAD_REQUEST);
		ResponseEntity<?> actualResponseEntity = storeApiController.retriveUserstore("username","token");
		
		assertEquals(responseEntity, actualResponseEntity);
		
	}
	
	
	@Test
	public void test_create_userstore() throws Exception {
		UserstoreEntity userstoreEntity = new UserstoreEntity();
		userstoreEntity.setStoreId("abc");
		//userstoreEntity.setUserId(567);
		when(tokenParser.getRole("token")).thenReturn("ADMIN");
		when(validator.validate("ADMIN", "create_userstore")).thenReturn(true);
		when(iStoreService.createUserstore(any(UserstoreEntity.class))).thenReturn(false);
		storeApiController.createUserstore(getUserstorePositiveRequest(),"token");
		
		storeApiController.createUserstore(getUserstoreNativeRequest(),"token");
		
	}
	
	
	
/*	@Test
	public void test_exception_create_userstore() throws Exception {
		String str = "ADMIN";
		UserstoreEntity userstoreEntity = null;
		when(tokenParser.getRole("token")).thenReturn(str);
		when(validator.validate("ADMIN", "create_userstore")).thenReturn(true);
		when(iStoreService.createUserstore(userstoreEntity)).thenReturn(true);
		
		ResponseEntity responseEntity = new ResponseEntity(getFailureStatus(), HttpStatus.BAD_REQUEST);
		assertEquals(responseEntity,storeApiController.createUserstore(getUserstorePositiveRequest(),"token"));
		
	}*/
	
	private ResponseMultiple getSearchResponse() {
		ResponseMultiple responseMultiple = new ResponseMultiple();

		List<Store> storeList = new ArrayList<Store>();

		Store store1 = new Store();
		store1.setName("str");
		store1.setAddress("TS");
		store1.setStoreid("storeid");
		Phone phone = new Phone();
		phone.setNumber("1234567890");
		phone.setCc("09");
		store1.setPhone(phone);

		storeList.add(store1);
		responseMultiple.setStores(storeList);
		responseMultiple.setStatus(getSuccessStatus());

		return responseMultiple;
	}
	
	private Status getSuccessStatus() {
		Status status = new Status();
		status.setOk(true);
		status.setHttp(Integer.valueOf((HttpStatus.OK).toString()));
		status.setWhy("request successfull");
		return status;
	}
	
	private Request getStoreRequest(){
		Request req = new Request();
		Store st = new Store();
		st.setName("str");
		st.setAddress("TS");
		st.setStoreid("storeid");
		Phone pe2 = new Phone();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		st.setPhone(pe2);
		req.setStore(st);
		return req;
	}
	
	private RequestUserstore getUserstorePositiveRequest() {
		RequestUserstore requestUserstore = new RequestUserstore();
		Userstore userstore = new Userstore();
		userstore.setStoreid("abc");
		userstore.setUserid(567);
		requestUserstore.setUserstore(userstore);
		return requestUserstore;
	}
	
	private RequestUserstore getUserstoreNativeRequest() {
		RequestUserstore requestUserstore = new RequestUserstore();
		Userstore userstore = new Userstore();
		userstore.setStoreid("storeid");
		userstore.setUserid(1);
		requestUserstore.setUserstore(userstore);
		return requestUserstore;
	}
	
	private Status getFailureStatus() {

		Status status = new Status();
		status.setOk(false);
		status.setHttp(Integer.valueOf((HttpStatus.BAD_REQUEST).toString()));
		status.setWhy("request failed");
		return status;

	}

}
