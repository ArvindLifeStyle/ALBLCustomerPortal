package com.arvind.customerPortal.security.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import com.arvind.customerPortal.domain.BusResource;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.RolesResource;
import com.arvind.customerPortal.domain.UsersRole;
import com.arvind.customerPortal.security.Validator;


@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

	@InjectMocks
	private Validator validator;
	
	@Mock
    private EntityManager entityManager;
	
	@Mock
	private Environment env;
	
	String queryRole="select r from BusRole r where r.role=?";
	String resourcesQuery="select b from BusResource b where b.resourceId=?";
	String roleResourceFetch="select a from RolesResource a where a.roleId=?";
	String roleIdQuery="select r.roleId from BusRole r where r.role=?";
	String findHasRole="select u from UsersRole u where u.userId=?1 and u.roleId=?2";
	
	@SuppressWarnings("unchecked")
	TypedQuery<BusRole> typedQueryForRole = Mockito.mock(TypedQuery.class);
	
	@SuppressWarnings("unchecked")
	TypedQuery<BusResource> typedQueryForResource = Mockito.mock(TypedQuery.class);
	
	@SuppressWarnings("unchecked")
	TypedQuery<RolesResource> queryResMap = Mockito.mock(TypedQuery.class);
	
	@SuppressWarnings("unchecked")
	TypedQuery<Integer> typedQueryForRoleIdQuery = Mockito.mock(TypedQuery.class);
	
	@SuppressWarnings("unchecked")
	TypedQuery<UsersRole> findhasRoleTypedQuery = Mockito.mock(TypedQuery.class);
	
	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		env = mock(Environment.class);
		entityManager = mock(EntityManager.class);
		Mockito.when(env.getProperty("role.fetch")).thenReturn(queryRole);
		Mockito.when(env.getProperty("role.resource.fetch")).thenReturn(roleResourceFetch);
		Mockito.when(env.getProperty("resource.fetch")).thenReturn(resourcesQuery);
		Mockito.when(env.getProperty("roleFetch.id")).thenReturn(roleIdQuery);
		Mockito.when(env.getProperty("userRoleMap")).thenReturn(findHasRole);
		
		ReflectionTestUtils.setField(validator, "env", env);
		ReflectionTestUtils.setField(validator, "entityManager", entityManager);
	}
	
	@Test
	public void testValidate()
	{
		List<BusRole> roles=new ArrayList<BusRole>();
		BusRole role=new BusRole();
		role.setRoleId(1);
		role.setRole("ADMIN");
		roles.add(role);
		
		Mockito.when(entityManager.createQuery(queryRole, BusRole.class)).thenReturn(typedQueryForRole);
		Mockito.when(typedQueryForRole.setParameter(1, "ADMIN")).thenReturn(typedQueryForRole);
		Mockito.when(typedQueryForRole.getResultList()).thenReturn(roles);
		
		List<RolesResource> roleResourceList=new ArrayList<RolesResource>();
		RolesResource roleToResource=new RolesResource();
		roleToResource.setId(1);
		roleToResource.setResourceId(1);
		roleToResource.setRoleId(1);
		roleResourceList.add(roleToResource);
		
		Mockito.when(entityManager.createQuery(roleResourceFetch,RolesResource.class)).thenReturn(queryResMap);
		Mockito.when(queryResMap.setParameter(1, 1)).thenReturn(queryResMap);
		Mockito.when(queryResMap.getResultList()).thenReturn(roleResourceList);
		
		BusResource tempBusResource=new BusResource();
		tempBusResource.setResourceId(1);
		tempBusResource.setResource("test");
		Mockito.when(entityManager.createQuery(resourcesQuery,BusResource.class)).thenReturn(typedQueryForResource);
		Mockito.when(typedQueryForResource.setParameter(1, 1)).thenReturn(typedQueryForResource);
		Mockito.when(typedQueryForResource.getSingleResult()).thenReturn(tempBusResource);
		
		boolean testFlag=false;
		testFlag=validator.validate("ADMIN", "test");
		assertTrue(testFlag);
	}
	
	@Test
	public void hasRoleTestFalse()
	{
		List<Integer> roleidList=new ArrayList<Integer>();
		roleidList.add(1);
		Mockito.when(entityManager.createQuery(roleIdQuery,Integer.class)).thenReturn(typedQueryForRoleIdQuery);
		Mockito.when(typedQueryForRoleIdQuery.setParameter(1, "test")).thenReturn(typedQueryForRoleIdQuery);
		Mockito.when(typedQueryForRoleIdQuery.getResultList()).thenReturn(roleidList);
		
		UsersRole testUsersRole=new UsersRole();
		List<UsersRole> testUsersRoleList=new ArrayList<UsersRole>();
		testUsersRole.setId(1);
		testUsersRole.setRoleId(1);
		testUsersRole.setUserId(1);
		Mockito.when(entityManager.createQuery(findHasRole,UsersRole.class)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.setParameter(1, 1)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.setParameter(2, 1)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.getResultList()).thenReturn(testUsersRoleList);
		boolean testFlag=false;
		testFlag=validator.hasRole(1, "test");
		assertFalse(testFlag);
	}
	
	@Test
	public void hasRoleTestTrue()
	{
		List<Integer> roleidList=new ArrayList<Integer>();
		roleidList.add(1);
		Mockito.when(entityManager.createQuery(roleIdQuery,Integer.class)).thenReturn(typedQueryForRoleIdQuery);
		Mockito.when(typedQueryForRoleIdQuery.setParameter(1, "test")).thenReturn(typedQueryForRoleIdQuery);
		Mockito.when(typedQueryForRoleIdQuery.getResultList()).thenReturn(roleidList);
		
		UsersRole testUsersRole=new UsersRole();
		List<UsersRole> testUsersRoleList=new ArrayList<UsersRole>();
		testUsersRole.setId(1);
		testUsersRole.setRoleId(1);
		testUsersRole.setUserId(1);
		testUsersRoleList.add(testUsersRole);
		Mockito.when(entityManager.createQuery(findHasRole,UsersRole.class)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.setParameter(1, 1)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.setParameter(2, 1)).thenReturn(findhasRoleTypedQuery);
		Mockito.when(findhasRoleTypedQuery.getResultList()).thenReturn(testUsersRoleList);
		boolean testFlag=false;
		testFlag=validator.hasRole(1, "test");
		assertTrue(testFlag);
	}


	
}
