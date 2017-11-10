package com.arvind.customerPortal.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.arvind.customerPortal.Dao.UserDao;
import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.domain.BusUser;
import com.arvind.customerPortal.exceptions.UserNotFoundException;
import com.arvind.customerPortal.model.LoginResult;
import com.arvind.customerPortal.service.impl.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@InjectMocks
	private LoginServiceImpl loginServiceImpl;
	
	@Mock
	private UserDao userDao;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(loginServiceImpl, "userDao", userDao);
	}
	
	@Test
	public void getLoginDetailsTest() throws UserNotFoundException
	{
		LoginRequestDTO loginDto=new LoginRequestDTO();
		loginDto.setId(1);
		loginDto.setPassword("test");
		loginDto.setUsername("test");
		loginDto.setRole("Admin");
		LoginResult loginResult=new LoginResult();
		loginResult.setOk("testOK");
		loginResult.setRole("testRole");
		
		LoginResult mockLoginResult=new LoginResult();
		Mockito.when(userDao.getLoginDetails(loginDto)).thenReturn(loginResult);
		mockLoginResult=loginServiceImpl.getLoginDetails(loginDto);
		assertNotNull(mockLoginResult);
		assertEquals(loginResult.getOk(), mockLoginResult.getOk());
		assertEquals(loginResult.getRole(), mockLoginResult.getRole());
	}
	
	@Test
	public void getUserDetailsTest()
	{
		String testString="test";
		List<BusUser> testList=new ArrayList<BusUser>();
		BusUser testUser=new BusUser();
		testUser.setUserId(1);
		testUser.setName("test");
		List<BusUser> mockList=new ArrayList<BusUser>();
		Mockito.when(userDao.getUserDetails(testString)).thenReturn(testList);
		mockList=loginServiceImpl.getUserDetails(testString);
		assertEquals(mockList, testList);
	}
}
