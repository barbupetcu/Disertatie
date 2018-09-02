package com.facultate.disertatie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_difficulty")
public class RefDifficulty{
	@Id
	@Column(name = "id", nullable = false, updatable=false)
	private int id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name="quota")
	private int quota;

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
	
	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}
}