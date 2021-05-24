package com.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.Repository.AccountRepository;
import com.blog.Repository.ArticleListRepository;
import com.blog.Repository.ArticleRepository;
import com.blog.Repository.ListContentRepository;
import com.blog.exception.ArticleNotFoundException;
import com.blog.model.Account;
import com.blog.model.ArticleList;
import com.blog.model.ListContent;
import com.blog.model.Article;
import com.blog.model.AString;

@RestController
public class Rest {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ArticleListRepository articlelistRepository;
	@Autowired
	ListContentRepository listcontentRepository;
	
	@PostMapping("/api/login")
	public Account login(@RequestBody Account account) {
		return accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());
	}
	
	//Get全部文章
	@GetMapping("/api/articles")
	public List<Article> ArticleAll() {
		return articleRepository.findAll();
	}

	//Get標題
	@GetMapping("/api/articles/search")
	public List<Article> ArticleBySearch(@RequestParam(value = "title") String title,
			@RequestParam(value = "category")String category){
		if(category.equals("所有"))
			return articleRepository.findByTitleContaining(title);
		return articleRepository.findByTitleContainingAndCategory(title, category);
	}
	
	//Post新文章
	@PostMapping("/api/articles")
	public Article newArticle(@RequestBody Article newArticle) {
		newArticle.setDate(getDateTime());
		return articleRepository.save(newArticle);
	}
	
	//Get藉由文章ID取得文章
	@GetMapping("/api/articles/{id}")
	public Article one(@PathVariable Long id) {
	    return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
	  }
	
	//Get使用者的文章列表清單
	@GetMapping("/api/listoflist")
	public List<ArticleList> ArticleListByUser(){
		return articlelistRepository.findByUserid((long)1);
	}
	
	//Get文章特定清單內容
	@GetMapping("/api/articlelist/{id}")
	public List<ListContent> ListContentById(@PathVariable Long id){
		return listcontentRepository.findById(id);
	}

	//Post將文章加進清單
	@PostMapping("/api/articlelist")
	public AString ListContentById(@RequestBody ListContent listcontent){
		List<ListContent> temp = listcontentRepository.findById(listcontent.getId());
		boolean test=false;
		for(ListContent i:temp) {
			if(i.getArticleid()==listcontent.getArticleid())
				test=true;
		}
		if(test)
			return new AString("已經存在此清單");
		Article article = articleRepository.findById(listcontent.getArticleid()).get();
		listcontent.setCategory(article.getCategory());
		listcontent.setTitle(article.getTitle());
		
		listcontentRepository.save(listcontent);
		
		return new AString("成功儲存");
	}
	
	//Get帳號
	@GetMapping("/api/accounts")
	public List<Account> AccountAll() {
		return accountRepository.findAll();
	}
	
	public String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}
}
