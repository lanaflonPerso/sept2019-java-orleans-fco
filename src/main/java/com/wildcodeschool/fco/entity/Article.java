package com.wildcodeschool.fco.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User author;
	private String title;
	private String content;
	private Long date;
	private String picture;
	
	//Constructor 
	
	public Article() {}
	
	
	public Article(Integer id, User author, String title, String content, Long date, String picture) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.date = date;
		this.picture = picture;
	}

	
	//Getters & Setters

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getDate() {
		return date;
	}


	public void setDate(Long date) {
		this.date = date;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}

}
