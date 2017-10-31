package com.abc.portal.service;

import java.util.List;

import com.abc.portal.models.UserRegister;
import com.abc.portal.repo.entity.UserInfo;

public interface UserService {

	UserInfo internalUserRegister(UserRegister userRegister);

	UserInfo externalUserRegister(UserRegister userRegister);

	List<UserInfo> getUsers(UserRegister userRegister);

}