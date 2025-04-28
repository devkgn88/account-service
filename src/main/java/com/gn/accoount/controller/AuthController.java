package com.gn.accoount.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gn.accoount.request.LoginRequest;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		System.out.println(loginRequest);
		return"연결 성공";
	}

}
