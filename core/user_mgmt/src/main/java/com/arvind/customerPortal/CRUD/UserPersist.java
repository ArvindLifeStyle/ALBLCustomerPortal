package com.arvind.customerPortal.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.domain.BusUser;

@Repository
public interface UserPersist extends JpaRepository<BusUser, Long> {

}
