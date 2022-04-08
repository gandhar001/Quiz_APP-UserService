package com.SpringBootApp_QuizApp.UserService.Api.Services.Definitions;

import java.util.Map;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.UserDTO;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO.UserData;



public interface JWTUserDetailsService extends UserDetailsService  {

	
   public Map<String,Object> registerUser(UserDTO user)throws Exception;
   public Map<String,Object> authenticateUser(JWTRequest authUser) throws Exception;
   
   public UserData findUserByUsername(String userName) throws Exception;
  
 
}
