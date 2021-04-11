package com.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Column(name="ID")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String user;
	private String title;
	private String category;
	private String content;
	@Column(name="CREATE_DATE")
	private String date;
	
	public Article(){}
	public Article(String user,String title, String category, String content) {
		this.user=user;
		this.title = title;
		this.category = category;
		this.content = content;
		this.date="null";
	}
	public Article(String user,String title, String category, String content, String date) {
		this.user=user;
		this.title = title;
		this.category = category;
		this.content = content;
		this.date = date;
	}

	
	public Article(String title, String category, String content) {
		this.title = title;
		this.category = category;
		this.content = content;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
