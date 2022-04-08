package com.SpringBootApp_QuizApp.UserService.Api.Controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.RequestDTO.UserDTO;
import com.SpringBootApp_QuizApp.UserService.Api.DTO.ResponseDTO.UserData;
import com.SpringBootApp_QuizApp.UserService.Api.Services.Implementation.JWTUserDetailsServiceImpl;
import com.SpringBootApp_QuizApp.Utils.ResponseDTO.ResDTO;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = ("${api.path}"))
public class UserController {

	@Autowired
	private JWTUserDetailsServiceImpl userService;

	@GetMapping(value = "{username}/fetch-userDetails")
	public ResponseEntity<UserData> fetchUserDetails(@PathVariable("username") String userName) throws Exception {
		return new ResponseEntity<UserData>(userService.findUserByUsername(userName), HttpStatus.OK);

	}

	@PostMapping(value = "register")
	public ResponseEntity<ResDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws Exception {
		return new ResponseEntity<ResDTO>(new ResDTO(userService.registerUser(userDTO)), HttpStatus.OK);
	}

	@PostMapping(value = "authenticate")
	public ResponseEntity<ResDTO> authenticateUser(@RequestBody @Valid JWTRequest authUser) throws Exception {
		return new ResponseEntity<ResDTO>(new ResDTO(userService.authenticateUser(authUser)), HttpStatus.OK);

	}

}
