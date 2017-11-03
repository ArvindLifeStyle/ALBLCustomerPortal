package com.arvind.customerPortal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arvind.customerPortal.Dao.InternalRegisterDAO;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.service.InternalRegisterService;

@Service
@Transactional
public class InternalRegisterServiceImpl implements InternalRegisterService {

	@Autowired
	private InternalRegisterDAO internalRegisterDao;
	@Override
	public boolean register(UserRegister register) {
		return internalRegisterDao.registerInternalUser(register);
	}

}
