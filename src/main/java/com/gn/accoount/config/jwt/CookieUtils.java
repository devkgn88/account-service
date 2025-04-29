package com.gn.accoount.config.jwt;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {
	
	public void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
		Cookie cookie = new Cookie("refresh_token", refreshToken);
		cookie.setHttpOnly(true);
		// https(ssl도입)되면 true로 바꾸기
		cookie.setSecure(false);
		cookie.setPath("/");
		// 7일동안 쿠키 유지
		cookie.setMaxAge(60*60*24*7);
		response.addCookie(cookie);
	}
	
	public String getRefreshTokenFromRequest(HttpServletRequest request) {
		if(request.getCookies() == null) return null;
		for(Cookie cookie : request.getCookies()) {
			if("refresh_token".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
