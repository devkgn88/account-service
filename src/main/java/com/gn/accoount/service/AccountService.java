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
	
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenService refreshTokenService;
		
	
	public JwtTokenInfo login(LoginRequest loginRequest) {
		
		// 1. 로그인시에 입력한 아이디와 비밀번호 추출
		String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
		// 2. 아이디와 비밀번호를 기반으로 Authentication 객체 생성
		// Authentication이 생성되는 순간에는 인증 여부 false
		UsernamePasswordAuthenticationToken authenticationToken 
			= new UsernamePasswordAuthenticationToken(username, password);
		
		// 3. 사용자 비밀번호 검증이 이루어지는 부분
		// authenticate 메소드가 실행될 때 AccountDetailsService의 loadUserByUsername 메소드가 돌아감		
		Authentication authentication 
			= authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 4. accessToken과 refreshToken 생성
		String accessToken = jwtTokenProvider.createAccessToken(authentication);
	    String refreshToken = jwtTokenProvider.createRefreshToken();
	    
	    // 5. Redis에 refreshToken 저장 (userId, refreshToken)
	    refreshTokenService.saveRefreshToken(username, refreshToken);
		
		// 6. AccessToken만 클라이언트에 보내주기
		return JwtTokenInfo.builder()
					.grantType("Bearer")
					.accessToken(accessToken)
					.build();
	}
	

}
