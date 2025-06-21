package com.hr.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class UserCompose 
{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column
	private int id;
	@Column
	private String subject;
	@Column
	private String text;
	
	@Column
	private String empName;
	
	@Column
	private int parentUkid;
	
	@Column
	private String status;
	
	@Column
	private String addedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	//We didn't added into database we simply use this column inside status.html page 
	@Transient  
	private String position;
	
	
	public UserCompose()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public int getParentUkid() {
		return parentUkid;
	}


	public void setParentUkid(int parentUkid) {
		this.parentUkid = parentUkid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getAddedDate() {
		return addedDate;
	}


	public void setAddedDate( String addedDate) {
		this.addedDate = addedDate;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	
	
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public UserCompose(int id, String subject, String text, String empName, int parentUkid, String status,
			String addedDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.text = text;
		this.empName = empName;
		this.parentUkid = parentUkid;
		this.status = status;
		this.addedDate = addedDate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}


	@Override
	public String toString() {
		return "Compose [id=" + id + ", subject=" + subject + ", text=" + text + ", empName=" + empName
				+ ", parentUkid=" + parentUkid + ", status=" + status + ", addedDate=" + addedDate + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

	
	
}

