package com.arvind.customerPortal.CRUD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.model.Store;


@Repository
public interface IStoreRepo extends JpaRepository<StoreEntity, Long>{
	
	List<StoreEntity> findByNameLike(String name);
	
	
	List<StoreEntity> findByStoreid(String storeId);
	
}
