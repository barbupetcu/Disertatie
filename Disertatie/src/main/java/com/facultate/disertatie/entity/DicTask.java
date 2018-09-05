package com.facultate.disertatie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="dic_task")
public class DicTask{
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_task_seq")
	@SequenceGenerator(name="id_task_seq", sequenceName = "id_task_seq", allocationSize=1)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="deadline")
	private LocalDateTime deadline;
	
	@Column(name="description")
	private String description;
	
	@Column(name="end_date")
	private LocalDateTime end_date;
	
	@JsonIgnore
    @Column(name="created", updatable=false)
	@CreationTimestamp
	private LocalDateTime created;
    
    @JsonIgnore
	@Column(name="modified")
	@UpdateTimestamp
	private LocalDateTime modified;
    
    @Column(name="points")
    private Integer points;
    
    @ManyToOne
    @JoinColumn(name="DIFFICULTY")
    private RefDifficulty difficulty;
    
    @ManyToOne
    @JoinColumn(name="PRIORITY")
    private RefPriority priority;
    
    @ManyToOne
    @JoinColumn(name="STATUS")
    private RefTaskStatus status;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="DELAY")
    private RefTaskEnd end_status;
    
    @ManyToOne
    @JoinColumn(name="TASK_ITERATION")
    private DicTaskIteration taskIteration;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private DicPerso user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public RefDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(RefDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public RefPriority getPriority() {
		return priority;
	}

	public void setPriority(RefPriority priority) {
		this.priority = priority;
	}

	public RefTaskStatus getStatus() {
		return status;
	}

	public void setStatus(RefTaskStatus status) {
		this.status = status;
	}

	public RefTaskEnd getEnd_status() {
		return end_status;
	}

	public void setEnd_status(RefTaskEnd end_status) {
		this.end_status = end_status;
	}

	public DicTaskIteration getTaskIteration() {
		return taskIteration;
	}

	public void setTaskIteration(DicTaskIteration taskIteration) {
		this.taskIteration = taskIteration;
	}

	public DicPerso getUser() {
		return user;
	}

	public void setUser(DicPerso user) {
		this.user = user;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
		
}