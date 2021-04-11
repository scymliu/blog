package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findByUser(String user);
}
