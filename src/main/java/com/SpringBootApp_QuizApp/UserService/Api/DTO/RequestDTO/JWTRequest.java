package com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO;


import javax.validation.constraints.NotEmpty;

public class JWTRequest {

	@NotEmpty(message = "username can't be null or empty.")
	private String username;
	
	@NotEmpty(message = "password can't be null or empty.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
