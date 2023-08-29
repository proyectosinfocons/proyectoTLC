package com.example.proyectotlc.controller;

import com.example.proyectotlc.common.EntityDtoConverter;
import com.example.proyectotlc.dto.*;
import com.example.proyectotlc.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
	@Autowired
	private EntityDtoConverter converter;


    @ApiOperation(value = "Registro de usuarios")
    @PostMapping("/signup")
	public ResponseEntity<UserRequest> signup(@RequestBody SignupRequest request){
		User user=userService.createUser(converter.convertDtoToEntity(request));
		return new ResponseEntity<UserRequest>(converter.convertEntityToDto(user), HttpStatus.CREATED);		
	}

    @ApiOperation(value = "Autenticacion de usuarios")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
       LoginResponse response=userService.login(request);
       return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }

    
    
   
	
}