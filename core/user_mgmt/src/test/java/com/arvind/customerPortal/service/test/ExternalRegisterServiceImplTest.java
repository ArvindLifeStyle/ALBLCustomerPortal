package com.arvind.customerPortal.service.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.arvind.customerPortal.Dao.ExternalRegisterDAO;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.impl.ExternalRegisterServiceImpl;

public class ExternalRegisterServiceImplTest {
	
	@InjectMocks
	ExternalRegisterServiceImpl externalRegisterServiceImpl;
	
	@Mock
	ExternalRegisterDAO externalRegisterDAO;
	
	
	
	
	@Before
	public void setUp()
	{
		
		MockitoAnnotations.initMocks(this);
		Mockito.when(externalRegisterDAO.registerExternalUser(new UserRegister())).thenReturn(true);
	}
	
	@Test
	public void testRegisterExternalUser()
	{
		boolean flag=false;
		flag=externalRegisterServiceImpl.register(new UserRegister());
		assertTrue(flag);
	}

}
