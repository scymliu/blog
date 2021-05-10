package com.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(ListContent.ListContentId.class)
@Entity
@Table(name = "list_content")
public class ListContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	@Id
	@Column(name = "article_id")
	private long articleid;
	private String title;
	private String category;

	public static class ListContentId implements Serializable {
		private static final long serialVersionUID = 1L;

		private long id;
		private long articleid;

		public ListContentId() {
		}

		public ListContentId(long id, long articleid) {
			super();
			this.id = id;
			this.articleid = articleid;
		}

		public long getArticleid() {
			return articleid;
		}

		public void setArticleid(long articleid) {
			this.articleid = articleid;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

	}

	public ListContent() {
	}

	public ListContent(String category) {
		this.category = category;
	}

	public ListContent(long id, long articleid, String title, String category) {
		this.id = id;
		this.articleid = articleid;
		this.title = title;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArticleid() {
		return articleid;
	}

	public void setArticleid(long articleid) {
		this.articleid = articleid;
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

}
