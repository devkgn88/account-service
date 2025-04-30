package com.gn.accoount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gn.accoount.config.jwt.JwtTokenInfo;
import com.gn.accoount.request.LoginRequest;
import com.gn.accoount.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AccountService accountService;
		
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        JwtTokenInfo tokenInfo = accountService.login(loginRequest, response);
        return ResponseEntity.ok(tokenInfo);      
	}
	
	@PostMapping("/api/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){
		JwtTokenInfo tokenInfo = accountService.refreshToken(request, response);
		if(tokenInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
		}else {
			return ResponseEntity.ok(tokenInfo);		
		}
	}

}
