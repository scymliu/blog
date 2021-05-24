package com.blog.exception;

public class AccountNotFoundException extends RuntimeException{

	public AccountNotFoundException(Long id) {
	    super("Could not find account " + id);
	  }
	public AccountNotFoundException(String email) {
	    super("Could not find account " + email);
	  }
}
