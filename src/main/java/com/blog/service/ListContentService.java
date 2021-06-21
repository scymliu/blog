package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repository.ListContentRepository;
import com.blog.model.Msg;
import com.blog.model.Article;
import com.blog.model.ListContent;

@Service
public class ListContentService {
	@Autowired
	ListContentRepository listcontentRepository;
	
	@Autowired
	ArticleService articleservice;
	
	public List<ListContent> ListContentById(Long id){
		return listcontentRepository.findById(id);
	}
	
	public Msg insert2list( ListContent listcontent){
		List<ListContent> temp = listcontentRepository.findById(listcontent.getId());
		boolean test=false;
		for(ListContent i:temp) {
			if(i.getArticleid()==listcontent.getArticleid())
				test=true;
		}
		if(test)
			return new Msg("已經存在此清單");
		Article article = articleservice.getbyid(listcontent.getArticleid());
		listcontent.setCategory(article.getCategory());
		listcontent.setTitle(article.getTitle());
		listcontent.setUser(article.getUser());
		listcontentRepository.save(listcontent);
		
		return new Msg("成功儲存");
	}
	
	public Msg delSelect(long id,long article_id) {
		listcontentRepository.deleteListContentByIdAndArticleid(id,article_id);
		return new Msg("刪除成功");
	}
	
	
	
}
