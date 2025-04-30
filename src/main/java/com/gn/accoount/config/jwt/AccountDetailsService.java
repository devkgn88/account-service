package com.gn.accoount.config.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gn.accoount.domain.Account;
import com.gn.accoount.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService{
	
	private final AccountRepository accountRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 	Account entity = accountRepository.findByAccountId(username);
	 	if(entity == null) {
			throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
		} 
		return new AccountDetails(entity);
		
    }
	 
}
