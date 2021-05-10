package com.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article_list")
public class ArticleList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="user_id")
	private long userid;
	private String name;
	
	public ArticleList() {}
	
	public ArticleList(String name) {
		this.name=name;
	}
	
	public ArticleList(long id, long userid, String name) {
		this.id = id;
		this.userid = userid;
		this.name = name;
	}
	
	
	
	public long getId() {
		return id;
	}
	public long getUser_id() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
