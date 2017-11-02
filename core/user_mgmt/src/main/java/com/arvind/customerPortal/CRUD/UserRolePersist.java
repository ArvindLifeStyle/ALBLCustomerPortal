package com.arvind.customerPortal.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.domain.UsersRole;

@Repository
public interface UserRolePersist extends JpaRepository<UsersRole, Long> {

}
