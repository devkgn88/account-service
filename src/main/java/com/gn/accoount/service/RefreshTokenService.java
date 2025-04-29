package com.gn.accoount.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	
	private final RedisTemplate<String,String> redisTemplate;
	
	private static final String PREFIX = "RT:"; // Redis에 저장할 때 앞에 붙일 접두사
	
    // RefreshToken 저장(Redis에서 7일 동안 살아있음)
    public void saveRefreshToken(String userId, String refreshToken) {
        String key = PREFIX + userId;
        redisTemplate.opsForValue().set(key, refreshToken, 1000 * 60 * 60 * 24 * 7, TimeUnit.MILLISECONDS);
    }

    // RefreshToken 조회
    public String getRefreshToken(String userId) {
        String key = PREFIX + userId;
        return redisTemplate.opsForValue().get(key);
    }

    // RefreshToken 삭제 (로그아웃 시)
    public void deleteRefreshToken(String userId) {
        String key = PREFIX + userId;
        redisTemplate.delete(key);
    }
}
