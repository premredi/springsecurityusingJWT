package com.spring.securty.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.securty.Jwt.JwtUtil;
import com.spring.securty.Model.UserInformation;
import com.spring.securty.Model.UserRequest;
import com.spring.securty.Model.UserResponse;
import com.spring.securty.Service.Userservice;

@RestController
@RequestMapping("/user")
public class Usercontroller {
	
	@Autowired
	private Userservice service;
	
	@Autowired
	private JwtUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveinfo(@RequestBody UserInformation info){
		Integer id= service.savedetails(info);
		String body="user "+id+" saved";
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginuser(@RequestBody UserRequest request){
		String token=util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Sucess ! generated"));
	}

}
