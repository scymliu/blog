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
	private String intro;
	
	public ArticleList() {}
	
	public ArticleList(String name,String intro) {
		this.name=name;
		this.intro=intro;
	}
	
	public ArticleList(long id, long userid, String name,String intro) {
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.intro=intro;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	
}
