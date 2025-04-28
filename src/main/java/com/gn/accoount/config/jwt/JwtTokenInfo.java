package com.gn.accoount.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenInfo {
	
	private String grantType;
    private String accessToken;
    private String refreshToken;

}
