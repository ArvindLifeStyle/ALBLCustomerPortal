package com.arvind.customerPortal.dao.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.arvind.customerPortal.CRUD.IStoreRepo;
import com.arvind.customerPortal.CRUD.IUserstoreRepo;
import com.arvind.customerPortal.Dao.impl.StoreDaoImpl;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.exceptions.DaoException;
import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.model.Store;

@RunWith(MockitoJUnitRunner.class)
public class StoreDaoTest {

	@Mock	
	IStoreRepo iStoreRepo;
	
	@Mock
	IUserstoreRepo iUserstoreRepo;

	@InjectMocks	
	private StoreDaoImpl storeDaoImpl;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void test_Create_Store() {
		StoreEntity se = new StoreEntity();
		se.setName("test_store");
		se.setAddress("test_storeAddress");
		PhoneEntity pe = new PhoneEntity();
		pe.setNumber("1234567890");
		pe.setCc("1234567890");
		se.setPhone(pe);
		when(iStoreRepo.save(any(StoreEntity.class))).thenReturn(se);
		
		assertTrue(se.equals(storeDaoImpl.createStore(se)));
		
		
		expectedEx.expect(DaoException.class);
		expectedEx.expectMessage("DaoException in searchStore");
		StoreEntity nullSe = null;
		when(iStoreRepo.save(any(StoreEntity.class))).thenReturn(nullSe);
		storeDaoImpl.createStore(nullSe);
		
	}
	
    @Test
    public void test_Should_Search_Store1() {
    	StoreEntity se = new StoreEntity();
		se.setName("test_store");
		se.setAddress("test_storeAddress");
		PhoneEntity pe = new PhoneEntity();
		pe.setNumber("1234567890");
		pe.setCc("1234567890");
		se.setPhone(pe); 
		
		StoreEntity se1 = new StoreEntity();
		se1.setName("test_store1");
		se1.setAddress("test_storeAddress1");
		PhoneEntity pe1 = new PhoneEntity();
		pe1.setNumber("1234567890");
		pe1.setCc("09");
		se1.setPhone(pe1); 
		
		StoreEntity se2 = new StoreEntity();
		se2.setName("test_store2");
		se2.setAddress("test_storeAddress2");
		PhoneEntity pe2 = new PhoneEntity();
		pe2.setNumber("1234567890");
		pe2.setCc("09");
		se2.setPhone(pe2); 
		
		List<StoreEntity> list = new ArrayList<StoreEntity>();
		
        
        list.add(se);
        list.add(se1);
        list.add(se2);
        
        final String queryString = "test_%";
        when(iStoreRepo.findByNameLike(queryString)).thenReturn(list);

        List<Store> sStoreList = storeDaoImpl.searchStore("test_");
        
        assertNotNull(sStoreList);
    }
    
    
    @Test
    public void test_Should_retrieve_Store() {
        final List<StoreEntity> list = new ArrayList<StoreEntity>();
        StoreEntity se = new StoreEntity();
		se.setName("MyQ");
		se.setAddress("test_storeAddress");
		PhoneEntity pe = new PhoneEntity();
		pe.setNumber("1234567890");
		pe.setCc("09");
		se.setPhone(pe); 
        list.add(se);
        
        final String storeId = "MyQ";
        when(iStoreRepo.findByStoreid(storeId)).thenReturn(list);
        
        List<Store> sStoreList = storeDaoImpl.retriveStore("MyQ");
        assertNotNull(sStoreList);
        assertTrue(list.equals(iStoreRepo.findByStoreid(storeId)));        
        assertFalse(list.equals(iStoreRepo.findByStoreid("abc")));
    }
    
    
	@Test
	public void test_Create_userStore() {
		UserstoreEntity re = new UserstoreEntity();
		re.setStoreId("Store1");
		re.setUserId(1);
		when(iUserstoreRepo.save(any(UserstoreEntity.class))).thenReturn(re);
		assertTrue(storeDaoImpl.createUserstore(re));
		
		UserstoreEntity nulluse = null;
		when(iUserstoreRepo.save(any(UserstoreEntity.class))).thenReturn(nulluse);
		assertFalse(storeDaoImpl.createUserstore(null));
	}
	
	@Test
	public void getStoreIdTest() {		
		List<UserstoreEntity> listUSEntity = new ArrayList<>();
		UserstoreEntity us = new UserstoreEntity();
		us.setStoreId("STR");
		listUSEntity.add(us);
		when(iUserstoreRepo.findByUserId(1)).thenReturn(listUSEntity);		
		String str = storeDaoImpl.getStoreId(1);
		assertNotNull(str);
		assertEquals("STR", str);
		
		expectedEx.expect(DaoException.class);
		expectedEx.expectMessage("Exception raised while fetching getStoreId");
		
		List<UserstoreEntity> listUSEntity1 = new ArrayList<>();
		UserstoreEntity us1 = new UserstoreEntity();
		us1.setStoreId("STR");
		listUSEntity.add(us);
		when(iUserstoreRepo.findByUserId(1)).thenReturn(listUSEntity1);		
		storeDaoImpl.getStoreId(2);
		
		
	}
	
	
}