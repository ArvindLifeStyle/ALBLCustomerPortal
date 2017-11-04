package com.arvind.customerPortal.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.Dao.UserDao;
import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.domain.UsersRole;
import com.arvind.customerPortal.exceptions.DaoException;
import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.Https;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.security.TokenGenerator;
import com.arvind.customerPortal.security.Validator;

@Repository
@PropertySource("classpath:application.properties")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	LoginResult result = new LoginResult();
	UsersRole usersRole = new UsersRole();
	BusRole busRole = new BusRole();

	@Autowired
	private Environment env;

	@Autowired
	private TokenGenerator tokenGenerator;
	
	@Autowired
	private Validator validator;

	// private String secret=env.getProperty("secret");
	private String getUserQuery;

	@Override
	public LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException{

		LoginResult logResult = new LoginResult();

		BCryptPasswordEncoder pwdChecker = new BCryptPasswordEncoder();
		List<BusUser> userList = new ArrayList<BusUser>();
		getUserQuery = env.getProperty("user.login");
		// (encrypt.encode(loginDto.getPassword()))
		userList = entityManager.createQuery(getUserQuery, BusUser.class).setParameter(1, loginDto.getUsername())
				.getResultList();

		if (userList.get(0).getActive().equals("true") && userList.get(0).getVerified().equals("true")) {
		if ((userList.size() == 1)) {
			String tempPass = userList.get(0).getPassword();

			if (pwdChecker.matches(loginDto.getPassword(), tempPass)) {
				userList.forEach(user -> {
					boolean flag=false;
					flag=validator.hasRole(user.getUserId(),loginDto.getRole());
					if (flag)
					{
					Https http$ = new Https();
					http$.setStatus((HttpStatus.OK).toString());
					logResult.setOk("true");
					logResult.setHttp$(http$);
					logResult.setWhy("request successful");
					logResult.setUserid(user.getUserId());
					logResult.setRole(loginDto.getRole());
					logResult.setToken(tokenGenerator.generate(loginDto, "secret"));
					}
					
					else
					{
						throw new DataNotFoundException("Incorrect Role");
					}

				});
				
				return logResult;

			} else {
				throw new UserNotFoundException();
			}
		} else
			throw new UserNotFoundException();
		}else {
			throw new DataNotFoundException("Mandatory Active and Verify user");
		}

	}
	
	public List<BusUser> getUserDetails(String uname) {

		List<BusUser> list = entityManager.createQuery("select e FROM BusUser e",BusUser.class).getResultList();
		
		return list;
	}
	
	public Integer getUserByName(String uname) {
		try {
		Integer list = entityManager.createQuery("select e.userId FROM BusUser e where name='"+uname+"'",Integer.class).getSingleResult();
		return list;
		}catch(DaoException dao) {
			throw new DaoException("Exception raised while getting User By Name");
		}
	}
}
