package com.abc.portal.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.abc.portal.exception.ResourceNotFoundException;
import com.abc.portal.models.UserRegister;
import com.abc.portal.repo.entity.UserInfo;
import com.abc.portal.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Authentication authenticate(Authentication authentication) {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		UserRegister userGetRequest = new UserRegister();
		userGetRequest.setName(userName);
		List<UserInfo> userInfo = userService.getUsers(userGetRequest);
		if (userInfo.isEmpty()) {
			throw new ResourceNotFoundException("Invalid login credentials");
		}

		// use the credentials and authenticate against the third-party system

		boolean userFlag = false;
		for (UserInfo user : userInfo) {
			if (user.getName().equals(userName)) {
				userFlag = true;
				break;
			}
		}
		boolean passwordFlag = passwordEncoder.matches(password, userInfo.iterator().next().getPassword());
		if (userFlag && passwordFlag) {

			logger.info("Succesful authentication!");

			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

			final SecureUser secureUser = new SecureUser(userInfo.iterator().next());
			return new UsernamePasswordAuthenticationToken(secureUser, password, grantedAuths);
		} else {
			logger.info("Login fail!");
			throw new RuntimeException("Invalid login credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}