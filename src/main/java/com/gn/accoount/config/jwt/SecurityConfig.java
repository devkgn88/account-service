package com.gn.accoount.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(csrf -> csrf.disable())
    		.cors(cors -> cors.configurationSource(CorsConfig.corsConfigurationSource()))
    		.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/login","/api/refresh").permitAll()
                    .anyRequest().authenticated()
            );
        return http.build();
    }
    
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
