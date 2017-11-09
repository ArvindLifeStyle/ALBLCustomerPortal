package com.arvind.customerPortal.controller.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CompositeFilter;

import com.arvind.customerPortal.api.controller.StoreApiController;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.exceptions.DaoException;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesRuntimeException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.Status;
import com.arvind.customerPortal.model.Store;
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
		
		assertTrue(responseEntity.equals(storeApiController.createStore(getRequest(),"token")));
		
		
		expectedEx.expect(InsufficientAuthoritiesRuntimeException.class);
		storeApiController.createStore(getRequest(),"token1");

	}
	
	private Status getSuccessStatus() {
		Status status = new Status();
		status.setOk(true);
		status.setHttp(Integer.valueOf((HttpStatus.OK).toString()));
		status.setWhy("request successfull");
		return status;
	}
	
	private Request getRequest(){
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

}
