package com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDTO {

	@NotEmpty(message = "username can't be null or empty.") 
	private String username;

	@NotEmpty(message = "firstName can't be null or empty.") 
	private String firstName;
	
	@NotEmpty(message = "password can't be null or empty.") 
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String lastName;
	
	@NotEmpty(message = "email can't be null or empty.")  
	@Email(message="email must be of email format.")
	private String email;
	

}
