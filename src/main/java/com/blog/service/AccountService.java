package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.AccountRepository;
import com.blog.model.Account;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public Account Auth(Account account) {
		return accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());
	}
	
}
