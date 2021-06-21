package com.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.service.*;
import com.blog.model.Account;
import com.blog.model.ArticleList;
import com.blog.model.ListContent;
import com.blog.model.Article;
import com.blog.model.Msg;

@RestController
public class Rest {
	
	@Autowired
	AccountService accountservice;
	@Autowired
	ArticleService articleservice;
	@Autowired
	ArticleListService articlelistservice;
	@Autowired
	ListContentService listcontentservice;
	
	//------------------帳號------------------//
	
	@PostMapping("/api/login")
	public Account AccountAuth(@RequestBody Account account) {
		return accountservice.Auth(account);
	}
		
	//------------------文章------------------//

	@GetMapping("/api/articles/search")
	public List<Article> ArticleBySearch(@RequestParam(value = "title") String title,@RequestParam(value = "category")String category){
		return articleservice.ArticleSearch(title, category);
	}
	@PostMapping("/api/articles")
	public Article WriteArticle(@RequestBody Article newArticle) {
		return articleservice.newArticle(newArticle);
	}
	@GetMapping("/api/articles/{id}")
	public Article one(@PathVariable Long id) {
	    return articleservice.getbyid(id);
	  }
	@GetMapping("/api/articles") 
	public List<Article> NewArticle(){
		return articleservice.NewestArticle();
	}
	@GetMapping("/api/articles/userpage") 
	public List<Article> UserNewArticle(@RequestParam(value = "user") String user){
		return articleservice.OwnArticleTop3(user);
	}
	@GetMapping("/api/articles/user/{user}")
	public List<Article> UserArticle(@PathVariable String user,@RequestParam(value = "requestuser") String requestuser){
		return articleservice.UserArticle(user, requestuser);
	}
	
	//----------------文章清單----------------//
	
	@GetMapping("/api/listoflist/{id}")
	public List<ArticleList> ArticleListByUser(@PathVariable Long id){
		return articlelistservice.UserList(id);
	}	
	@PostMapping("/api/newlist")
	public Msg NewList(@RequestBody ArticleList articleList){
		return articlelistservice.newList(articleList);
	}	
	
	//----------------清單內容----------------//
	
	@GetMapping("/api/articlelist/{id}")
	public List<ListContent> ListContentById(@PathVariable Long id){
		return listcontentservice.ListContentById(id);
	}
	@PostMapping("/api/articlelist")
	public Msg Insert2list(@RequestBody ListContent listcontent){
		return listcontentservice.insert2list(listcontent);
	}
	@GetMapping("/api/articlelist/delete/{id}")
	public Msg DelListContent(@PathVariable Long id, @RequestParam(value="articleid") Long articleid) {
		return listcontentservice.delSelect(id, articleid);
	}

}
