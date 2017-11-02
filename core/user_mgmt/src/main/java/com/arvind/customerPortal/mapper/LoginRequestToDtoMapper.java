package com.arvind.customerPortal.mapper;

import com.arvind.customerPortal.Dto.LoginRequestDTO;
import com.arvind.customerPortal.model.LoginRequest;

public class LoginRequestToDtoMapper {
	
	public LoginRequestDTO mapLogintoDto(LoginRequest request)
	{
		LoginRequestDTO loginRequestDTO=new LoginRequestDTO();
		//String tempRole= String.valueOf((request.getRole()));
		loginRequestDTO.setRole(String.valueOf((request.getRole())).toString());
		loginRequestDTO.setUsername(request.getUsername());
		loginRequestDTO.setPassword(request.getPassword());
		return loginRequestDTO;
	}

}
