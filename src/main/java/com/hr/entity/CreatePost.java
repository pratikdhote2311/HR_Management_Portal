package com.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="CREATE_POSTS")
public class CreatePost
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private int id;
	@NotNull
	@Column(name="TITLE")
	private String title;
	@Column(name="COMMENT",length=2000)
	@NotNull
	private String comment;

	private String addedDate;
	
	public CreatePost()
	{
		
	}

	public CreatePost(int id, String title, String comment, String addedDate) {
		super();
		this.id = id;
		this.title = title;
		this.comment = comment;
		this.addedDate = addedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "CreatePost [id=" + id + ", title=" + title + ", comment=" + comment + ", addedDate=" + addedDate + "]";
	}
	
	
	
	
	
}
