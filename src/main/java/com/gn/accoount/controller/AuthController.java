package com.gn.accoount.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gn.accoount.config.jwt.JwtTokenInfo;
import com.gn.accoount.request.LoginRequest;
import com.gn.accoount.service.AccountService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AccountService accountService;
		
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        JwtTokenInfo tokenInfo = accountService.login(loginRequest);
        return ResponseEntity.ok(tokenInfo);
                
	}

}
