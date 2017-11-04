package com.arvind.customerPortal.CRUD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.model.Userstore;

@Repository
public interface IUserstoreRepo extends JpaRepository<UserstoreEntity, Long>{

	List<UserstoreEntity> findByUserId(int storeId);
}
