package com.arvind.customerPortal.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginRequest;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.security.EncryptionAgent;
import com.arvind.customerPortal.security.TokenGenerator;


@Repository
@PropertySource("classpath:application.properties")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	LoginResult result=new LoginResult();
	UsersRole usersRole=new UsersRole();
	BusRole busRole=new BusRole();
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@Autowired
	private EncryptionAgent encrypt;
	
    //private String secret=env.getProperty("secret");
	private String getUserQuery;
	private String roleQuery;
	
	@Override
	public LoginResult getLoginDetails(LoginRequestDTO loginDto) throws UserNotFoundException {
		BCryptPasswordEncoder pwdChecker=new BCryptPasswordEncoder();
		List<BusUser> userList=new ArrayList<BusUser>();
		getUserQuery=env.getProperty("user.login");
		//(encrypt.encode(loginDto.getPassword()))
		userList=entityManager.createQuery(getUserQuery,BusUser.class).setParameter(1, loginDto.getUsername()).getResultList();
		
		
		
		if((userList.size()==1))
		{
			String tempPass=userList.get(0).getPassword();
			
			if(pwdChecker.matches(loginDto.getPassword(),tempPass))
			{
    userList.forEach(user->{
    	
	result.setCustomerName(user.getBusUsercol());
	result.setErrorMessage("");;
	result.setStatusMessage("success");;
	result.setToken(tokenGenerator.generate(loginDto,"secret"));
	result.setStatusCode((HttpStatus.OK).toString());
});
		
			}
else
{
	throw new UserNotFoundException();
}
	}
		return result;


	}}
