package com.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.ArticleRepository;
import com.blog.exception.ArticleNotFoundException;
import com.blog.model.Article;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> ArticleAll() {
		return articleRepository.findAll();
	}

	public List<Article> ArticleSearch(String title,String category){
		if(category.equals("所有"))
			return articleRepository.findByTitleContainingOrderByDateDesc(title);
		return articleRepository.findByTitleContainingAndCategoryOrderByDateDesc(title, category);
	}

	public List<Article> UserArticle(String user,String requestuser) {
		if(requestuser.equals(user))
			return articleRepository.findByUserOrderByDateDesc(user);
		return articleRepository.findByUserAndPrivacyOrderByDateDesc(user, false);
	} 
	
	public List<Article> NewestArticle() {
		return articleRepository.findTop10ByPrivacyOrderByDateDesc(false);
	}
	
	public List<Article> OwnArticleTop3(String user){
		return articleRepository.findTop3ByUserAndPrivacyOrderByDateDesc(user,false);
	}
	
	public Article newArticle(Article newArticle) {
		newArticle.setDate(getDateTime());
		return articleRepository.save(newArticle);
	}
	
	public Article getbyid(Long id) {
	    return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
	 }
	
	public String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}
}
