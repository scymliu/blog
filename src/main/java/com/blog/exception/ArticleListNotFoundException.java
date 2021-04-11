package com.blog.exception;

public class ArticleListNotFoundException extends RuntimeException{
	public ArticleListNotFoundException(Long id) {
	    super("Could not find articlelist " + id);
	  }
}
