package com.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.Repository.AccountRepository;
import com.blog.Repository.ArticleRepository;
import com.blog.exception.ArticleNotFoundException;
import com.blog.model.Account;
import com.blog.model.Article;

@RestController
public class Rest {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/api/articles")
	public List<Article> ArticleAll() {
		return articleRepository.findAll();
	}

	@GetMapping("/api/accounts")
	public List<Account> AccountAll() {
		return accountRepository.findAll();
	}
	
	@PostMapping("/api/articles")
	Article newArticle(@RequestBody Article newArticle) {
		return articleRepository.save(newArticle);
	}

	@GetMapping("/api/articles/{id}")
	Article one(@PathVariable Long id) {
	    return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
	  }

	
}
