package com.arvind.customerPortal.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.arvind.customerPortal.CRUD.UserRolePersist;
import com.arvind.customerPortal.Dao.impl.UserDaoImpl;
import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.DaoException;
import com.arvind.customerPortal.exceptions.DataNotFoundException;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.security.EncryptionAgent;
import com.arvind.customerPortal.security.TokenGenerator;
import com.arvind.customerPortal.security.Validator;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {
	@InjectMocks
	private UserDaoImpl userDaoImpl;

	@Mock
	private TokenGenerator tokenGenerator;

	@Mock
	private Validator validator;

	@Mock
	private UserRolePersist userRolePersist;
	

	private EntityManager entityManager;
	
	@Mock
	private Environment env;


	@SuppressWarnings("unchecked")
	TypedQuery<BusRole> typedQueryForRole = Mockito.mock(TypedQuery.class);

	@SuppressWarnings("unchecked")
	TypedQuery<BusUser> typedQueryForBusUser = Mockito.mock(TypedQuery.class);
	
	@SuppressWarnings("unchecked")
	TypedQuery<Integer> typedQueryForInt = Mockito.mock(TypedQuery.class);


	String rolefetch = "select r from BusRole r where r.role=?";
	String getUserQuery = "select e FROM BusUser e where e.email=?";

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
		
		EncryptionAgent agent=new EncryptionAgent();
		
		BusUser busUser=new BusUser();
		busUser.setEmail("test@test.com");
		busUser.setUserId(1);
		busUser.setPassword(agent.encode("testing"));
		busUser.setActive("true");
		busUser.setVerified("true");
		
		List<BusUser> userList=new ArrayList<BusUser>();
		userList.add(busUser);

		ReflectionTestUtils.setField(userDaoImpl, "tokenGenerator", tokenGenerator);
		ReflectionTestUtils.setField(userDaoImpl, "validator", validator);
		ReflectionTestUtils.setField(userDaoImpl, "entityManager", entityManager);
		ReflectionTestUtils.setField(userDaoImpl, "env", env);
		//ReflectionTestUtils.setField(userDaoImpl, "pwdChecker", pwdChecker);
		
		// mocking entity manager of BusRole class
		when(entityManager.createQuery(rolefetch, BusRole.class)).thenReturn(typedQueryForRole);
		when(typedQueryForRole.setParameter(1, "ADMIN")).thenReturn(typedQueryForRole);
		when(typedQueryForRole.getResultList()).thenReturn(roles);
		String uname="test";
		int testVal=1;
		// mocking entity manager of BusUser class
		when(entityManager.createQuery(getUserQuery, BusUser.class)).thenReturn(typedQueryForBusUser);
		when(entityManager.createQuery("select e FROM BusUser e", BusUser.class)).thenReturn(typedQueryForBusUser);
		when(entityManager.createQuery("select e.userId FROM BusUser e where name='"+uname+"'",Integer.class)).thenReturn(typedQueryForInt);
		when(typedQueryForInt.getSingleResult()).thenReturn(testVal);
		when(typedQueryForBusUser.setParameter(1, "test@test.com")).thenReturn(typedQueryForBusUser);
		when(typedQueryForBusUser.getResultList()).thenReturn(userList);
		when(env.getProperty("role.fetch")).thenReturn(rolefetch);
		when(env.getProperty("user.login")).thenReturn(getUserQuery);
		
		
		
	}	
	
	@Test
	public void RegisterExternalUserTest() throws UserNotFoundException
	{
		//BCryptPasswordEncoder pwdChecker = new BCryptPasswordEncoder();
		LoginResult testResult=new LoginResult();
		LoginRequestDTO testDto=new LoginRequestDTO();
		testDto.setUsername("test@test.com");
		testDto.setPassword("testing");
		testDto.setId(1);
		testDto.setRole("ADMIN");
		
		Mockito.when(validator.hasRole(1,testDto.getRole())).thenReturn(true);
		testResult=userDaoImpl.getLoginDetails(testDto);
		assertEquals("true", testResult.getOk());
		assertEquals("request successful", testResult.getWhy());
		assertEquals(1, testDto.getId());
		
		
		//nagative role checking
		testDto.setRole("ADMIN123");		
		expected.expect(DataNotFoundException.class);
		Mockito.when(validator.hasRole(1,testDto.getRole())).thenReturn(false);
		userDaoImpl.getLoginDetails(testDto);

	}
	
	@Test
	public void RegisterExternalUserWrongPasswordTest() throws UserNotFoundException
	{
		LoginRequestDTO testDto=new LoginRequestDTO();
		testDto.setUsername("test@test.com");
		testDto.setPassword("testing1");
		testDto.setId(1);
		testDto.setRole("ADMIN");
		
		expected.expect(UserNotFoundException.class);		
		Mockito.when(validator.hasRole(1,testDto.getRole())).thenReturn(true);
		userDaoImpl.getLoginDetails(testDto);
	}
	
	@Test
	public void RegisterExternalUserEmptyTest() throws UserNotFoundException
	{
		LoginRequestDTO testDto=new LoginRequestDTO();
		testDto.setUsername("test1@test.com");
		testDto.setPassword("testing1");
		testDto.setId(1);
		testDto.setRole("CUSTOMER");
		
		// mocking entity manager of BusRole class
		when(typedQueryForBusUser.setParameter(1, "test1@test.com")).thenReturn(typedQueryForBusUser);
		when(typedQueryForBusUser.getResultList()).thenReturn(Collections.emptyList());
		expected.expect(UserNotFoundException.class);		
		Mockito.when(validator.hasRole(1,testDto.getRole())).thenReturn(true);
		userDaoImpl.getLoginDetails(testDto);
	}
	
	
	@Test
	public void testGetUserDetails()
	{
		List<BusUser> testList=new ArrayList<BusUser>();
		testList=userDaoImpl.getUserDetails("test");
		assertNotNull(testList);
	}
	
	@Test
	public void testGetUserDetailsByName()
	{
		Integer testInt;
		testInt=userDaoImpl.getUserByName("test");
		assertNotNull(testInt);
	}
	
	@Test(expected=DaoException.class)
	public void testDaoException()
	{
		String uname="test";
		when(entityManager.createQuery("select e.userId FROM BusUser e where name='"+uname+"'",Integer.class)).thenReturn(typedQueryForInt);
		when(typedQueryForInt.getSingleResult()).thenThrow(new DaoException("test Exception"));
		int testInt=userDaoImpl.getUserByName("test");
		
	}
		
}
		
		
	