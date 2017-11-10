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
import com.arvind.customerPortal.Dao.InternalRegisterDAO;
import com.arvind.customerPortal.model.UserRegister;
import com.arvind.customerPortal.service.ExternalRegisterService;
import com.arvind.customerPortal.service.impl.ExternalRegisterServiceImpl;
import com.arvind.customerPortal.service.impl.InternalRegisterServiceImpl;

public class InternalRegisterServiceImplTest {
	
	@InjectMocks
	InternalRegisterServiceImpl internalRegisterServiceImpl;
	
	@Mock
	InternalRegisterDAO internalRegisterDAO;
	
	
	
	
	@Before
	public void setUp()
	{
		
		MockitoAnnotations.initMocks(this);
		Mockito.when(internalRegisterServiceImpl.register(new UserRegister())).thenReturn(true);
	}
	
	@Test
	public void testRegisterExternalUser()
	{
		boolean flag=false;
		flag=internalRegisterServiceImpl.register(new UserRegister());
		assertTrue(flag);
	}

}


