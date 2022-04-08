package com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO;

import java.beans.JavaBean;

import org.springframework.security.core.userdetails.UserDetails;

@JavaBean
public class UserDetailsDTO {
	private UserDetails userDetails;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	

	public UserDetailsDTO(UserDetails userDetails) {
		super();
		this.userDetails = userDetails;
	}

}
