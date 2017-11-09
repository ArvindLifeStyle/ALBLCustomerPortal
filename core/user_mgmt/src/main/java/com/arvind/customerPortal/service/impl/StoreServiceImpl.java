
package com.arvind.customerPortal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arvind.customerPortal.Dao.IStoreDao;
import com.arvind.customerPortal.Dao.UserDao;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.service.IStoreService;

@Service
public class StoreServiceImpl implements IStoreService{

	@Autowired
	private IStoreDao iStoreDao;
	
	@Autowired
	private UserDao iUserDao;
	
	public StoreEntity createStore(StoreEntity se) {
		
		return iStoreDao.createStore(se);
	}
	@Override
	public List<Store> searchStore(String s) {
		return iStoreDao.searchStore(s);
	}
	
	public boolean createUserstore(UserstoreEntity userstoreEntity){
		return iStoreDao.createUserstore(userstoreEntity);
	}
	@Override
	public String getStoreId(int userid) {
		
		return iStoreDao.getStoreId(userid);
		
	}
	@Override
	public List<Store> retrieveStore(String storeid) {
		return iStoreDao.retriveStore(storeid);
	}
	
	public Integer getUserByName(String uname) {
		return iUserDao.getUserByName(uname);
	}
}
