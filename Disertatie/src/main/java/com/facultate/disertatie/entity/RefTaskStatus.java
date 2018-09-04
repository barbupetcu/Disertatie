package com.facultate.disertatie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ref_task_status")
public class RefTaskStatus{
	@Id
	@Column(name = "id", nullable = false, updatable=false)
	private int id;
	
	@JsonIgnore
	@Column(name ="name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}