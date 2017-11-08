package com.arvind.customerPortal.dao.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.arvind.customerPortal.CRUD.IStoreRepo;
import com.arvind.customerPortal.CRUD.IUserstoreRepo;
import com.arvind.customerPortal.Dao.IStoreDao;
import com.arvind.customerPortal.Dao.impl.StoreDaoImpl;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(IStoreDao.class)
public class StoreDaoTest {

	@Mock
	IStoreRepo iStoreRepo;
	
	@Mock
	IUserstoreRepo iUserstoreRepo;

	@InjectMocks
	private StoreDaoImpl storeDaoImpl;
	
	
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
	}
	
    @Test
    public void test_Should_Search_Store1() {
    	StoreEntity se = new StoreEntity();
		se.setName("test_store");
		
		StoreEntity se1 = new StoreEntity();
		se1.setName("test_store1");
		
		StoreEntity se2 = new StoreEntity();
		se1.setName("astore2");
		
		List<StoreEntity> list = new ArrayList<StoreEntity>();
		
        
        list.add(se);
        list.add(se1);
        list.add(se2);
        
        final String queryString = "test_";
        when(iStoreRepo.findByNameLike(queryString)).thenReturn(list);

        assertTrue(list.equals(iStoreRepo.findByNameLike(queryString)));
      
    }
    
    
    @Test
    public void test_Should_retrieve_Store() {
        final List<StoreEntity> list = new ArrayList<StoreEntity>();
        StoreEntity se = new StoreEntity();
        se.setStoreid("MyQ");
        list.add(se);
        
        final String storeId = "MyQ";
        when(iStoreRepo.findByStoreid(storeId)).thenReturn(list);

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
	}
	
	
}