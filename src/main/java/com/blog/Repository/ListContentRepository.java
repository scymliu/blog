package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.blog.model.ListContent;

public interface ListContentRepository extends JpaRepository<ListContent, ListContent.ListContentId>{
	List<ListContent> findById(Long id);
	
	
	@Modifying
    @Transactional
	void deleteListContentByIdAndArticleid(Long id,Long articleid);
	
}
