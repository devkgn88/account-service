package com.gn.accoount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gn.accoount.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByAccountId(String username);
}
