package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findTop10ByPrivacyOrderByDateDesc(boolean privacy);
	
	List<Article> findByUserOrderByDateDesc(String user);
	
	List<Article> findByUserAndPrivacyOrderByDateDesc(String user,boolean privacy);
	
	List<Article> findByTitleContainingOrderByDateDesc(String title);
	
	List<Article> findByTitleContainingAndCategoryOrderByDateDesc(String title,String category);
	
	List<Article> findTop3ByUserAndPrivacyOrderByDateDesc(String user,boolean privacy);
}
