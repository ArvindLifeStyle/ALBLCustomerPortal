package com.arvind.customerPortal.controller.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.arvind.customerPortal.api.controller.StoreApiController;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.service.IStoreService;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreApiController.class)
public class StoreTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IStoreService iStoreService;

	@Ignore
	@Test
	@WithMockUser
	public void test_create_store() throws Exception {
		com.arvind.customerPortal.domain.StoreEntity storeEntity = new com.arvind.customerPortal.domain.StoreEntity();
		storeEntity.setName("test");
		storeEntity.setAddress("testAddress");
		storeEntity.setStoreid("StoreId");
		PhoneEntity pe = new PhoneEntity();
		pe.setNumber("9000123456");
		pe.setCc("09");
		storeEntity.setPhone(pe);
		when(iStoreService.createStore(any(StoreEntity.class))).thenReturn(storeEntity);
		
		mockMvc.perform(post("/store/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(getFileContents("createstorereq.json"))).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(getFileContents("createstoreres.json")));
	}
	
	
	private String getFileContents(String fileName) {
		try {
			return IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}