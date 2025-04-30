package com.gn.accoount.config.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{
	
	// 클라이언트 요청시 JWT를 인증하는 필터
	// 시큐리티를 통과하려면 2단계 필터를 거쳐야 함
	// 1. JwtAuthenticationFilter
	// 2. UsernamePasswordAuthenticationFilter
	private final JwtTokenProvider jwtTokenProvider;
	 
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String path = httpRequest.getServletPath();
	    
	    // 로그인과 토큰 재발급 요청은 JWT 필터를 타지 않고 바로 통과
	    List<String> excludedPaths = List.of("/api/login", "/api/refresh");
	    if (excludedPaths.contains(path)) {
	        chain.doFilter(request, response);
	        return;
	    }
		
		// 나머지 요청은 토큰 검사
        // Request Header 에서 JWT 추출
        String token = resolveToken((HttpServletRequest) request);

        // Token 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        return null;
    }
}
