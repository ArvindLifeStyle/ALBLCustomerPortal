package com.arvind.customerPortal.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arvind.customerPortal.Dao.UserDao;
import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;

@Service
@Transactional
public class LoginServiceImpl implements com.arvind.customerPortal.service.LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException {
		LoginResult loginResult = new LoginResult();
		loginResult = userDao.getLoginDetails(loginDto);
		return loginResult;
	}

}
