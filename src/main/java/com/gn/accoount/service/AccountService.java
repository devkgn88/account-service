package com.gn.accoount.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.gn.accoount.config.jwt.JwtTokenInfo;
import com.gn.accoount.config.jwt.JwtTokenProvider;
import com.gn.accoount.repository.AccountRepository;
import com.gn.accoount.request.LoginRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenInfo login(LoginRequest loginRequest) {
		
		String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
		// 1. id와 pw를 기반으로 Authentication 객체 생성
		// Authentication이 생성되는 순간에는 인증 여부 false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// 2. 사용자 비밀번호 검증이 이루어지는 부분
		// authenticate 메소드가 실행될 때 AccountDetailsService의 loadUserByUsername 메소드가 돌아감		
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		// 3. 인증 정보를 기반으로 JWT 토근 생성
		JwtTokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		return tokenInfo;
	}
	

}
