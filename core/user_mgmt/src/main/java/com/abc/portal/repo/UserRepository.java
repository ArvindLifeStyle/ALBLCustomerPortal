package com.abc.portal.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.abc.portal.repo.entity.UserInfo;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UserInfo, Long> {

	List<UserInfo> findByName(String name);

}