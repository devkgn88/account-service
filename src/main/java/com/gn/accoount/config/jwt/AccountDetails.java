package com.gn.accoount.config.jwt;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gn.accoount.domain.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private final Account account;
	
	public Account getAccount() {
		return account;
	}
	
	@Override
	public String getUsername() {
		return account.getAccountId();
	}

	@Override
	public String getPassword() {
		return account.getAccountPw();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roleName = "ROLE_STUDENT";
		if(account.getTeam().getTeamType().equals("M")) {
			roleName = "ROLE_MANAGER";
		} else if(account.getTeam().getTeamType().equals("T")) {
			roleName = "ROLE_TEACHER";
		}
		return List.of(new SimpleGrantedAuthority(roleName));
	}


	
	
}