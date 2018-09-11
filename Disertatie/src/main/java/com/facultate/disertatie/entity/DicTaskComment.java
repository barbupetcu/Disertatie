package com.facultate.disertatie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="DIC_TASK_COMMENT")
public class DicTaskComment{
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_task_comm_seq")
	@SequenceGenerator(name="id_task_comm_seq", sequenceName = "id_task_comm_seq", allocationSize=1)
	private long id;
	
	@Column(name="TASK_COMMENT")
	private String comment;
	
	@Formula(value="to_char(created,'hh24:mi')")
	private String hourString;
	
    @Column(name="created", updatable=false)
	@CreationTimestamp
	private LocalDateTime created;
    
    @ManyToOne
    @JoinColumn(name="TASK_ID")
    private DicTask task;
    
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private DicPerso user;
    
	public String getHourString() {
		return hourString;
	}

	public void setHourString(String hourString) {
		this.hourString = hourString;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public DicTask getTask() {
		return task;
	}

	public void setTask(DicTask task) {
		this.task = task;
	}

	public DicPerso getUser() {
		return user;
	}

	public void setUser(DicPerso user) {
		this.user = user;
	}        
}