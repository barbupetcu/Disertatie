package com.facultate.disertatie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "DIC_PERSO")
public class AppPerso {
	@Id
    private Long id;
	
	@Column(name = "nume")
	private String name;
	
	@Column(name = "prenume")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "adress")
	private String adress;
	
	@Column(name = "phone")
	private String phone;
	
	@JsonProperty("dept")
	@ManyToOne
	@JoinColumn(name="dept", nullable=false)
	private Dept dept;
	
	@JsonIgnore
    @Column(name="created")
	@CreationTimestamp
	private LocalDateTime createDateTime;
    
    @JsonIgnore
	@Column(name="modified")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private AppUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = user.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
