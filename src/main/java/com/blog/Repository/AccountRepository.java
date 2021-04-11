package com.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.model.Account;

public interface AccountRepository  extends JpaRepository<Account, Long>{

	Account findByEmail(String email);
	Account findByUsername(String username);
}
