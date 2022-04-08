package com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO;

public class UserResDTO {

	private String userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;	
	
	private String proFactor;
	
	
	
	public UserResDTO(String userId,String username, String firstName, String lastName, String email, String proFactor) {
		this.userId=userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.proFactor = proFactor;
	}
	public String getProFactor() {
		return proFactor;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setProFactor(String proFactor) {
		this.proFactor = proFactor;
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
}
