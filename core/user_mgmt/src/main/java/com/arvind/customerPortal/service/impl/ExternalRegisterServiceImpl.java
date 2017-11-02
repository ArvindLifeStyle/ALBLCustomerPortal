package com.arvind.customerPortal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arvind.customerPortal.Dao.ExternalRegisterDAO;
import com.arvind.customerPortal.Dao.InternalRegisterDAO;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.InternalRegisterService;


@Service
@Transactional
public class ExternalRegisterServiceImpl implements ExternalRegisterService {

	@Autowired
	private ExternalRegisterDAO externalRegisterDao;
	@Override
	public boolean register(UserRegister register) {
		boolean flag;
		flag=externalRegisterDao.registerExternalUser(register);
		return flag;
	}

}