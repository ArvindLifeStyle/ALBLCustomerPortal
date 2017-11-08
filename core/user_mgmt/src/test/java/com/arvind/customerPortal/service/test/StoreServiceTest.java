package com.arvind.customerPortal.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.arvind.customerPortal.Dao.IStoreDao;
import com.arvind.customerPortal.Dao.impl.StoreDaoImpl;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.service.IStoreService;
import com.arvind.customerPortal.service.impl.StoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(IStoreService.class)
public class StoreServiceTest {
	
	@InjectMocks
	StoreServiceImpl storeServiceImpl; 
	
	@Mock
	StoreDaoImpl storeDaoImpl;
	
	@Mock
	IStoreDao iStoreDao;
	
	@Mock
	StoreEntity storeEntity;
	
	@Test
	public void createStore_test() {
		
		when(iStoreDao.createStore(any(StoreEntity.class))).thenReturn(getStoreentity());
		
		StoreEntity result = storeServiceImpl.createStore(getStoreentity());
		assertNotNull(result);
		
		assertEquals(result.getName(),"service_test");
		assertEquals(result.getAddress(),"Ts1");
		assertEquals(result.getStoreid(),"9999888");
		assertEquals(result.getPhone().getCc(),"09");
		assertEquals(result.getPhone().getNumber(),"9000123456");
		
	}
	
	@Test
	public void createStore_nagativeTest() {
		
		StoreEntity se_null = null;		
		when(iStoreDao.createStore(any(StoreEntity.class))).thenReturn(se_null);		
		StoreEntity result2 = storeServiceImpl.createStore(se_null);
		assertNull(result2);
	}
	
	@Test
	public void searchStore_test() {
		
		
		List<Store> se1 = new ArrayList<Store>();
		se1.add(getStore());
		when(iStoreDao.searchStore(eq("service_test"))).thenReturn(se1);		
		assertTrue(se1.equals(storeServiceImpl.searchStore("service_test")));
		
		//nagative test cases
		List<Store> empty = new ArrayList<Store>();
		when(iStoreDao.searchStore(eq("service_empty_test"))).thenReturn(empty);
		assertTrue(empty.equals(storeServiceImpl.searchStore("service_empty_test")));
		
	}
	
	
	@Test
	public void retrieveStore_test() {
		
		List<Store> se1 = new ArrayList<Store>();
		se1.add(getStore());
		when(iStoreDao.retriveStore(eq("Store_001"))).thenReturn(se1);		
		assertTrue(se1.equals(storeServiceImpl.retrieveStore("Store_001")));
		
		//nagative test cases
		List<Store> empty = new ArrayList<Store>();
		when(iStoreDao.retriveStore(eq("Store_empty_001"))).thenReturn(empty);		
		assertTrue(empty.equals(storeServiceImpl.retrieveStore("Store_empty_001")));
		
		
	}
	
	
	@Test
	public void createuserStore_test() {
		
		UserstoreEntity se1 = new UserstoreEntity();
		se1.setStoreId("storeID");
		se1.setUserId(1);
		
		when(iStoreDao.createUserstore(any(UserstoreEntity.class))).thenReturn(true);
		
		boolean result = storeServiceImpl.createUserstore(se1);
		assertNotNull(result);
		assertTrue(result);
		
		
		when(storeDaoImpl.createUserstore(any(UserstoreEntity.class))).thenReturn(true);		
		boolean result1 = storeDaoImpl.createUserstore(se1);
		assertTrue(result1);
		
	}
	

	@Test
	public void createUserstore_nagativeTest() {
		
		UserstoreEntity se_null = null;
		when(iStoreDao.createUserstore(any(UserstoreEntity.class))).thenReturn(false);
		boolean result2 = storeServiceImpl.createUserstore(se_null);
		assertNotNull(result2);
		assertFalse(result2);
	}
	
	
	private StoreEntity getStoreentity(){
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setName("service_test");
		storeEntity.setAddress("Ts1");
		storeEntity.setStoreid("9999888");
		PhoneEntity pe = new PhoneEntity();
		pe.setNumber("9000123456");
		pe.setCc("09");
		storeEntity.setPhone(pe);
		return storeEntity;
	}
	
	private Store getStore(){
		Store storeEntity = new Store();
		storeEntity.setName("service_test");
		storeEntity.setAddress("Ts1");
		storeEntity.setStoreid("9999888");
		Phone pe = new Phone();
		pe.setNumber("9000123456");
		pe.setCc("09");
		storeEntity.setPhone(pe);
		return storeEntity;
	}
	
}
