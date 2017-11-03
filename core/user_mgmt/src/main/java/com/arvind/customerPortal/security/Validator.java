package com.arvind.customerPortal.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import com.arvind.customerPortal.constants.AuthsConstants;
import com.arvind.customerPortal.domain.BusResource;
import com.arvind.customerPortal.domain.BusRole;
import com.arvind.customerPortal.domain.RolesResource;
import com.arvind.customerPortal.domain.UsersRole;


@Component
public class Validator {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManager entityManager;
	

	
	private boolean auth=false;
	
	public boolean validate(String Role)
	{
		
		String queryRole=env.getProperty("role.fetch");
		String queryResMap=env.getProperty("role.resource.fetch");
		String resourcesQuery=env.getProperty("resource.fetch");
		List<BusRole> roles=new ArrayList<BusRole>();
		List<RolesResource> resourcesidList=new ArrayList<RolesResource>();
		List<BusResource> resourceList=new ArrayList<BusResource>();
		roles=entityManager.createQuery(queryRole, BusRole.class).setParameter(1, Role).getResultList();
		if(roles.size()>0)
		{
			resourcesidList=entityManager.createQuery(queryResMap,RolesResource.class).setParameter(1, roles.get(0).getRoleId()).getResultList();
			if(resourcesidList.size()>0)
			{
				List<Integer> tempIdList=new ArrayList<Integer>();
				resourcesidList.forEach(idList->{
					tempIdList.add(idList.getResourceId());
					
				});
				
				for(int counter:tempIdList)
				{
					BusResource tempBusResource=new BusResource();
					tempBusResource=(BusResource)entityManager.createQuery(resourcesQuery,BusResource.class).setParameter(1,counter).getSingleResult();
					resourceList.add(tempBusResource);
				}
				resourceList.forEach(resFinalList->{
					if(resFinalList.getResource().equals(AuthsConstants.USER_REGISTRATION))
						auth=true;
				});
				
				
				//resourceList=entityManager.createQuery(resourcesQuery,BusResource.class).setParameter(1, roles.get(0).getRoleId()).getResultList();
			}
		}
		return auth;
	}

	public boolean hasRole(int userId, String role) {
		int i=0;
		String roleIdQuery=env.getProperty("roleFetch.id");
		List<Integer> roleidList=new ArrayList<Integer>();
		roleidList=entityManager.createQuery(roleIdQuery,Integer.class).setParameter(1,role).getResultList();
		
		List<UsersRole> roleUserList=new ArrayList<UsersRole>();
		
			String findHasRole=env.getProperty("userRoleMap");
			for(int roleid:roleidList)
			{
			roleUserList=entityManager.createQuery(findHasRole,UsersRole.class).setParameter(1,userId).setParameter(2, roleid).getResultList();
			if(roleUserList.size()>0)
			{
				i++;
			}
			
			}
			
			if(i>0)
			{
				return true;
			}
		
		return false;
	}
	

	
	

}
