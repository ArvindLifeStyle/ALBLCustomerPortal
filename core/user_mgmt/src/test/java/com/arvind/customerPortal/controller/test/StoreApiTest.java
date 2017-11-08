package com.arvind.customerPortal.controller.test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CompositeFilter;

import com.arvind.customerPortal.api.StoreApi;
import com.arvind.customerPortal.api.controller.StoreApiController;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.ResponseMultiple;
import com.arvind.customerPortal.model.Status;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.service.impl.StoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(StoreApi.class)
public class StoreApiTest {

	private MockMvc mockMvc;

	@InjectMocks
	StoreApi storeApi;

	@Mock
	private StoreServiceImpl iStoreService;

	@Mock
	StoreApiController storeApiController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(storeApi).addFilters(new CompositeFilter()).build();
	}

	@Test
	public void test_create_store1() throws Exception {

		when(storeApiController.createStore(any(Request.class), eq("1234")))
				.thenReturn(new ResponseEntity(getSuccessStatus(), HttpStatus.OK));

		mockMvc.perform(post("/store/create").header("Authorization", "1234")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(getFileContents("createstorereq.json")))
				.andExpect(status().isOk());

		mockMvc.perform(post("/store/create").header("Authorization", "1234")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(getFileContents("createstorereq.json")))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(getFileContents("successres.json")));
	}

	@Test
	public void test_search_store() throws Exception {

		String pathParam = "auth_";
		when(storeApiController.searchStore(eq(pathParam), eq("123456")))
				.thenReturn(Mockito.mock(new ResponseEntity(getSearchResponse(), HttpStatus.OK).getClass()));

	}

	@Test
	public void test_retrieve_store() throws Exception {

		String pathParam = "auth_";
		when(storeApiController.searchStore(eq(pathParam), eq("123456")))
				.thenReturn(Mockito.mock(new ResponseEntity(getSearchResponse(), HttpStatus.OK).getClass()));

		MvcResult mv = mockMvc
				.perform(post("/store/search/{name}", pathParam).header("Authorization", "1234567")
						.contentType(MediaType.APPLICATION_JSON_UTF8).content(""))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void test_create_userstore() throws Exception {

		when(storeApiController.createUserstore(any(RequestUserstore.class), eq("1234")))
				.thenReturn(new ResponseEntity(getSuccessStatus(), HttpStatus.OK));

		mockMvc.perform(post("/userstore/create").header("Authorization", "1234")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(getFileContents("createstorereq.json")))
				.andExpect(status().isOk());

		mockMvc.perform(post("/userstore/create").header("Authorization", "1234")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(getFileContents("createuserstorereq.json")))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(getFileContents("successres.json")));
	}

	private Status getSuccessStatus() {
		Status status = new Status();
		status.setOk(true);
		status.setHttp(Integer.valueOf((HttpStatus.OK).toString()));
		status.setWhy("request successfull");
		return status;
	}

	private Status getFailureStatus() {

		Status status = new Status();
		status.setOk(false);
		status.setHttp(Integer.valueOf((HttpStatus.BAD_REQUEST).toString()));
		status.setWhy("request failed");
		return status;

	}

	private ResponseMultiple getSearchResponse() {
		ResponseMultiple responseMultiple = new ResponseMultiple();

		List<Store> storeList = new ArrayList<Store>();

		Store store = new Store();
		store.setName("auth_store1");
		store.setAddress("Ts1");
		store.setStoreid("9999888");
		Phone pe = new Phone();
		pe.setNumber("9000123456");
		pe.setCc("09");
		store.setPhone(pe);

		Store store1 = new Store();
		store1.setName("auth_store2");
		store1.setAddress("Ts2");
		store1.setStoreid("999900888");
		Phone phone = new Phone();
		phone.setNumber("9000123456");
		phone.setCc("09");
		store1.setPhone(phone);

		responseMultiple.setStores(storeList);
		responseMultiple.setStatus(getSuccessStatus());

		return responseMultiple;
	}

	private String getFileContents(String fileName) {
		try {
			return IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}