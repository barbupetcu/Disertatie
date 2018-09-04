package com.facultate.disertatie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="DIC_TASK_ITERATION")
public class DicTaskIteration{
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_task_iter_seq")
	@SequenceGenerator(name="id_task_iter_seq", sequenceName = "id_task_iter_seq", allocationSize=1)
	private long id;
	
	@Column(name="START_DATE")
	private LocalDateTime startDate;
	
	@Column(name="END_DATE")
	private LocalDateTime endDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}