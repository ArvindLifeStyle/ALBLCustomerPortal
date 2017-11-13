package com.arvind.customerPortal.dao.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import com.arvind.customerPortal.CRUD.UserPersist;
import com.arvind.customerPortal.CRUD.UserRolePersist;
import com.arvind.customerPortal.Dao.impl.ExternalRegisterDaoImpl;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.domain.UsersRole;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.security.EncryptionAgent;

@RunWith(MockitoJUnitRunner.class)
public class ExternalRegisterDaoImplTest {
	@InjectMocks
	private ExternalRegisterDaoImpl externalRegisterDaoImpl;

	@Mock
	private EncryptionAgent encrypt;

	@Mock
	private UserPersist userPersist;

	@Mock
	private UserRolePersist userRolePersist;
	

	private EntityManager entityManager;
	
	@Mock
	private Environment env;


	@SuppressWarnings("unchecked")
	TypedQuery<BusRole> typedQueryForRole = Mockito.mock(TypedQuery.class);

	@SuppressWarnings("unchecked")
	TypedQuery<BusUser> typedQueryForBusUser = Mockito.mock(TypedQuery.class);


	String rolefetch = "select r from BusRole r where r.role=?";
	String userFind = "select e FROM BusUser e where e.email=?";

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		env = mock(Environment.class);
		entityManager = mock(EntityManager.class);
		
		BusRole role=new BusRole();
		role.setRole("ADMIN");
		role.setRoleId(1);
		List<BusRole> roles=new ArrayList<BusRole>();
		roles.add(role);
		
		BusUser busUser=new BusUser();
		busUser.setEmail("test@test.com");
		busUser.setUserId(1);
		
		List<BusUser> userList=new ArrayList<BusUser>();
		userList.add(busUser);

		ReflectionTestUtils.setField(externalRegisterDaoImpl, "encrypt", encrypt);
		ReflectionTestUtils.setField(externalRegisterDaoImpl, "userPersist", userPersist);
		ReflectionTestUtils.setField(externalRegisterDaoImpl, "entityManager", entityManager);
		ReflectionTestUtils.setField(externalRegisterDaoImpl, "env", env);
		
		// mocking entity manager of BusRole class
		when(entityManager.createQuery(rolefetch, BusRole.class)).thenReturn(typedQueryForRole);
		when(typedQueryForRole.setParameter(1, "ADMIN")).thenReturn(typedQueryForRole);
		when(typedQueryForRole.getResultList()).thenReturn(roles);
		
		// mocking entity manager of BusUser class
		when(entityManager.createQuery(userFind, BusUser.class)).thenReturn(typedQueryForBusUser);
		when(typedQueryForBusUser.setParameter(1, "test@test.com")).thenReturn(typedQueryForBusUser);
		when(typedQueryForBusUser.getResultList()).thenReturn(userList);
		when(env.getProperty("role.fetch")).thenReturn(rolefetch);
		when(env.getProperty("user.find")).thenReturn(userFind);
		/*ReflectionTestUtils.setField(externalRegisterDaoImpl, "encrypt", encrypt);
		ReflectionTestUtils.setField(externalRegisterDaoImpl, "userPersist", userPersist);
		ReflectionTestUtils.setField(externalRegisterDaoImpl, "entityManager", entityManager);*/
		

	}	
	
	@Test
	public void RegisterExternalUserTest()
	{
		boolean flag=false;
		Mockito.when(userRolePersist.saveAndFlush(new UsersRole())).thenReturn(new UsersRole());
		UserRegister userRegister=new UserRegister();
		userRegister.setEmail("test@test.com");
		userRegister.setName("test");
		userRegister.setPassword("test");
		userRegister.setRole("ADMIN");
		flag=externalRegisterDaoImpl.registerExternalUser(userRegister);
		assertEquals(true, flag);
		
		flag=false;
		UserRegister userRegister123=new UserRegister();
		userRegister123.setRole("ADMIN123");
		when(typedQueryForRole.setParameter(1, "ADMIN123")).thenReturn(typedQueryForRole);
		when(typedQueryForRole.getResultList()).thenReturn(Collections.emptyList());
		flag=externalRegisterDaoImpl.registerExternalUser(userRegister123);
		assertEquals(false, flag);
		
		flag=false;
		Mockito.when(userRolePersist.saveAndFlush(new UsersRole())).thenReturn(new UsersRole());
		UserRegister nullUser = null;
		flag=externalRegisterDaoImpl.registerExternalUser(nullUser);
		assertEquals(false, flag);
	}
	
}
		
		
	