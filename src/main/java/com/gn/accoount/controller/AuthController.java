package com.gn.accoount.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gn.accoount.config.jwt.JwtTokenInfo;
import com.gn.accoount.domain.Account;
import com.gn.accoount.request.LoginRequest;
import com.gn.accoount.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AccountService accountService;
		
	@PostMapping("/api/auth/login")
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
	
	@GetMapping("/api/profile")
	public ResponseEntity<Map<String, Object>> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
		String accountId = userDetails.getUsername();
		Account account = accountService.getProfile(accountId);

        Map<String, Object> result = new HashMap<>();
        result.put("accountid", account.getAccountId());
        result.put("accountName", account.getAccountName());
        result.put("teamName", account.getTeam().getTeamName());

        return ResponseEntity.ok(result);
    }
	

}
