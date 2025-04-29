package com.gn.accoount.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
	@Bean
	LettuceConnectionFactory connectionFactory() {	// Redis와 통신할 수 있게 만드는 연결 객체 설정
		// Redis 서버와의 연결을 담당하는 팩토리 클래스
		// Lettuce 클라이언트 사용
		return new LettuceConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, String> redisTemplate(){
		RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(connectionFactory());
		
		// key, value 모두 문자열로 저장
		redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
	}
	
}
