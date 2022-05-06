package com.SpringBootApp_QuizApp.UserService.Api.Services.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBootApp_ExceptionHandling.Quiz_AppExceptionHandling.ExceptionHandling.ExceptionClasses.DatabaseException;
import com.SpringBootApp_ExceptionHandling.Quiz_AppExceptionHandling.ExceptionHandling.ExceptionClasses.ResourceNotFoundException;
import com.SpringBootApp_ExceptionHandling.Quiz_AppExceptionHandling.ExceptionHandling.ExceptionClasses.UnableToSaveException;
import com.SpringBootApp_QuizApp.Quiz_AppDataStore.Entities.UserEntity;
import com.SpringBootApp_QuizApp.Quiz_AppDataStore.Repositories.UserDao;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.UserDTO;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO.UserData;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO.UserResDTO;
import com.SpringBootApp_QuizApp.UserService.Api.Services.Definitions.JWTUserDetailsService;
import com.SpringBootApp_QuizApp.UserService.Api.Utils.JwtToken;


@Service
public class JWTUserDetailsServiceImpl implements JWTUserDetailsService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JWTUserDetailsServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtToken jwtToken;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserEntity user = null;
		UserDetails userDetails;
		try {
			user = userDao.findByUsername(userName);

		}
		catch(NullPointerException ex) {
			logger.error("User does not exist with username",userName, ex);

			throw new ResourceNotFoundException("username", userName, "User not found with username");
		}catch (Exception ex) {
			logger.error("Error occured while fetching user details with username", ex);
			throw new DatabaseException("usename", userName, "Error occured while fetching user details with username");
		}
		

		userDetails = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		return userDetails;

	}

	public UserData findUserByUsername(String userName) {
		UserEntity user = null;
		UserData userDetails;
		try {
			user = userDao.findByUsername(userName);

		} 
		catch(NullPointerException ex) {
			logger.error("User does not exist with username",userName, ex);

			throw new ResourceNotFoundException("username", userName, "User not found with username");
		}
		catch (Exception ex) {
			logger.error("Error occured while fetching user details with username", ex);
			throw new DatabaseException("usename", userName, "Error occured while fetching user details with username");
		}
		

		userDetails = new UserData(new ArrayList<>(), user.getPassword(), user.getUsername(), true, true, true, true);
		return userDetails;

	}

	@Override
	public Map<String, Object> registerUser(UserDTO user) {

		String userId = null;

		Map<String, Object> registerUserRes = new HashMap<>();
		UserEntity newUser = null;

		try {
			
			newUser = userDao.save(new UserEntity(user.getUsername(), user.getFirstName(), user.getLastName(),
					user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword())));
			userId = String.valueOf(newUser.getUserId());

			registerUserRes.put("userId", userId);

			logger.info("User with user id " + userId + "created successfully");

		} catch (Exception ex) {
			logger.error("Exception occured while registering new user:", ex);
			throw new UnableToSaveException("User unable to register.");
		}

		return registerUserRes;

	}

	public Map<String, Object> authenticateUser(JWTRequest authUser) {

		Map<String, Object> resMap = new HashMap<>();
		UserDetails userDetails;
		String authToken = null;
		UserResDTO loggedInUser = null;
		String userName;
		UserEntity fetchedUser = null;

		userName = authUser.getUsername();
		try {
			fetchedUser = userDao.findByUsername(userName);

		} 
		catch(NullPointerException ex) {
			logger.error("User does not exist with username",userName, ex);

			throw new ResourceNotFoundException("username", authUser.getUsername(), "User not found with username");
		}
		catch (Exception ex) {
			logger.error("Error occured while fetching user with username", ex);
			throw new DatabaseException("username", authUser.getUsername(),
					"Error occured while fetching user with username");
		}

		
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));

		userDetails = loadUserByUsername(authUser.getUsername());

		authToken = jwtToken.generateToken(userDetails);

		resMap.put("authToken", authToken);
		loggedInUser = new UserResDTO(String.valueOf(fetchedUser.getUserId()), fetchedUser.getUsername(),
				fetchedUser.getFirstName(), fetchedUser.getLastName(), fetchedUser.getEmail(),
				String.valueOf(fetchedUser.getProFactor()));

		resMap.put("loggedInUser", loggedInUser);

		return resMap;
	}

	
	

}
