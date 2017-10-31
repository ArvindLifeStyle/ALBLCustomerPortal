package com.abc.portal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.portal.models.UserRegister;
import com.abc.portal.repo.UserRepository;

import com.abc.portal.repo.entity.UserInfo;
import com.abc.portal.service.CodeGeneratorService;
import com.abc.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CodeGeneratorService codeGeneratorService;

	@Autowired
	private UserInfo user;

	@Override
	public UserInfo internalUserRegister(final UserRegister userRegister) {

		user.setId(Long.parseLong(codeGeneratorService.generate("seq_ab_userinfo")));
		user.setNick(userRegister.getName());
		user.setName(userRegister.getName());
		user.setEmail(userRegister.getEmail());
		user.setPassword(userRegister.getPassword());
		user.setActive(true);
		user.setVerified(true);

		return userRepository.save(user);
	}

	@Override
	public UserInfo externalUserRegister(UserRegister userRegister) {

		user.setId(Long.parseLong(codeGeneratorService.generate("seq_ab_userinfo")));
		user.setNick(userRegister.getName());
		user.setName(userRegister.getName());
		user.setEmail(userRegister.getEmail());
		user.setPassword(userRegister.getPassword());
		user.setActive(true);
		user.setVerified(true);

		return userRepository.save(user);
	}

	@Override
	public List<UserInfo> getUsers(final UserRegister userRegister) {

		return userRepository.findByName(userRegister.getName());

	}

}
