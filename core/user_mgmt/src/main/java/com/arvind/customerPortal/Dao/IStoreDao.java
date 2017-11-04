package com.arvind.customerPortal.Dao;

import java.util.List;

import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.Userstore;

public interface IStoreDao {

	StoreEntity createStore(StoreEntity store);
	
	List<Store> searchStore(String name);
	
	boolean createUserstore(UserstoreEntity userstoreEntity);
	
	public String getStoreId(int userid);
	
	List<Store> retriveStore(String storeid);
}
