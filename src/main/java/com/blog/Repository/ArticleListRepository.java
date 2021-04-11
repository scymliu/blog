package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.ArticleList;

public interface ArticleListRepository extends JpaRepository<ArticleList, Long>{
	List<ArticleList> findByUserid(Long userid);
}
