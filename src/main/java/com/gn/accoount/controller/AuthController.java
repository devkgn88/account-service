package com.gn.accoount.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gn.accoount.request.LoginRequest;
import com.gn.accoount.response.TokenResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
	
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
        
        
        if ("testuser".equals(username) && "testpassword".equals(password)) {
            return ResponseEntity.ok().body(new TokenResponse("1"));
        } else {
            return ResponseEntity.status(401).body("인증 오류");
        }
	}

}
