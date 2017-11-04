package com.arvind.customerPortal.service;


import java.util.List;

import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.Userstore;

public interface IStoreService {
	
	
	StoreEntity createStore(StoreEntity se);
	
	List<Store> searchStore(String s);
	
	List<Store> retrieveStore(String storeid);


	public boolean createUserstore(UserstoreEntity userstoreEntity);
	
	
	public String getStoreId(int userid);
	
	public Integer getUserByName(String uname);
	
}
