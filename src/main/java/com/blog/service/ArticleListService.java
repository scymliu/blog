package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.Repository.ArticleListRepository;
import com.blog.model.ArticleList;
import com.blog.model.Msg;

@Service
public class ArticleListService {
	
	@Autowired
	ArticleListRepository articlelistRepository;
	
	public List<ArticleList> UserList(@PathVariable Long id){
		return articlelistRepository.findByUserid(id);
	}
	
	public Msg newList(ArticleList articleList) {
		if(articlelistRepository.save(articleList)!=null)
			return new Msg("新增成功");
		return new Msg("新增失敗");
	}
	
}
