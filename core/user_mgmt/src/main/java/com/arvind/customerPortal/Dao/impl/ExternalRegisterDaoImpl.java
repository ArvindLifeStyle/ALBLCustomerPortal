package com.arvind.customerPortal.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.arvind.customerPortal.CRUD.UserPersist;
import com.arvind.customerPortal.CRUD.UserRolePersist;
import com.arvind.customerPortal.Dao.ExternalRegisterDAO;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.domain.UsersRole;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.security.EncryptionAgent;

@Repository
@PropertySource("classpath:application.properties")
public class ExternalRegisterDaoImpl implements ExternalRegisterDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EncryptionAgent encrypt;
	
	@Autowired
	private UserPersist userPersist;
	
	@Autowired
	private UserRolePersist userRolePersist;
	
	private String roleQuery=null;
	private int userid;

	//private BusUser temp=new BusUser();
	@Override
	public boolean registerExternalUser(UserRegister user) {
		BusUser bususer=new BusUser();
		bususer.setActive("1");
		bususer.setCreatedDtime(new Date());
		bususer.setCreatedUser("system");
		bususer.setEmail(user.getEmail());
		bususer.setLastModifiedDtime(new Date());
		bususer.setLastModifiedUser("system");
		bususer.setName(user.getName());
		bususer.setNick(user.getName());
		bususer.setPassword(encrypt.encode(user.getPassword()));
		userPersist.saveAndFlush(bususer);
		
		List<BusUser> templist=new ArrayList<BusUser>();
		templist=entityManager.createQuery(env.getProperty("user.find"),BusUser.class).setParameter(1, user.getEmail())/*.setParameter(2, encrypt.encode(user.getPassword()))*/.getResultList();
		templist.forEach(temp->{
			userid=temp.getUserId();
		});
		
		roleQuery=env.getProperty("role.fetch");
		List<BusRole> roles=new ArrayList<BusRole>();
		roles=entityManager.createQuery(roleQuery, BusRole.class).setParameter(1, user.getRole()).getResultList();
		roles.forEach(role->{
			UsersRole userrole=new UsersRole();
			userrole.setRoleId(role.getRoleId());
			userrole.setUserId(userid);
			userrole.setCreatedBy("system");
			userrole.setCreatedDtime(new Date());
			userrole.setLastModifiedBy("system");
			userrole.setLastModifiedDtime(new Date());
			userRolePersist.saveAndFlush(userrole);
		});
		return true;
	}

}
