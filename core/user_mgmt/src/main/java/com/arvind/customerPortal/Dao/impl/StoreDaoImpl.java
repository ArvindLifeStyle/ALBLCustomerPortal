package com.arvind.customerPortal.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.CRUD.IStoreRepo;
import com.arvind.customerPortal.CRUD.IUserstoreRepo;
import com.arvind.customerPortal.Dao.IStoreDao;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.exceptions.DaoException;
import com.arvind.customerPortal.model.Phone;
import com.arvind.customerPortal.model.Store;

@Repository
@Transactional
public class StoreDaoImpl implements IStoreDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	IStoreRepo iStoreRepo;

	@Autowired
	IUserstoreRepo iUserstoreRepo;

	public StoreEntity createStore(StoreEntity store) {
		logger.info("At createStore method in StoreDaoImpl");
		

			if(null != iStoreRepo.save(store))
				return iStoreRepo.save(store);
			else
				throw new DaoException("DaoException in searchStore");
	}

	@Override
	public List<Store> searchStore(String s) {
		logger.info("At searchStore method in StoreDaoImpl");
		List<Store> list = new ArrayList<>();
		for (StoreEntity se : iStoreRepo.findByNameLike("" + s + "%")) {
			Store internalSE = new Store();
			Phone phoneEntity = new Phone();
			internalSE.setName(se.getName());
			internalSE.setAddress(se.getAddress());
			internalSE.setStoreid(se.getStoreid());
			phoneEntity.setNumber(se.getPhone().getNumber());
			phoneEntity.setCc(se.getPhone().getCc());
			internalSE.setPhone(phoneEntity);

			list.add(internalSE);
		}
		return list;
	}

	@Override
	public List<Store> retriveStore(String storeid) {

		logger.info("At retriveStore method in StoreDaoImpl");
		List<Store> list = new ArrayList<>();
		for (StoreEntity se : iStoreRepo.findByStoreid(storeid)) {
			Store internalSE = new Store();
			Phone phoneEntity = new Phone();
			internalSE.setName(se.getName());
			internalSE.setAddress(se.getAddress());
			internalSE.setStoreid(se.getStoreid());
			phoneEntity.setNumber(se.getPhone().getNumber());
			phoneEntity.setCc(se.getPhone().getCc());
			internalSE.setPhone(phoneEntity);

			list.add(internalSE);
		}
		return list;

	}

	@Override
	public boolean createUserstore(UserstoreEntity userstoreEntity) {

		logger.info("At createUserstore method in StoreDaoImpl");

		UserstoreEntity list = iUserstoreRepo.save(userstoreEntity);
		if (null == list)
			return false;
		return true;

	}

	@Override
	public String getStoreId(int userid) {
		logger.info("At getStoreId method in StoreDaoImpl");
		List<UserstoreEntity> useList = iUserstoreRepo.findByUserId(userid);
		if(useList.isEmpty()) {
			throw new DaoException("Exception raised while fetching getStoreId");
		}else
			return useList.iterator().next().getStoreId();

	}

}
