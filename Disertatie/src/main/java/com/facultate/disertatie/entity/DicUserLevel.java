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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="DIC_USER_LEVEL")
public class DicUserLevel{
	@Id
	@Column(name="USER_ID", nullable=false, updatable=false)
	private long id;
	
	@Column(name="TOTAL_POINTS")
	private int totalPoints;
	
	@Column(name="PERCENTAGE_LEVEL")
	private int perLevel;
	
	@JsonIgnore
    @Column(name="created", updatable=false)
	@CreationTimestamp
	private LocalDateTime created;
	
	@JsonIgnore
    @Column(name="MODIFIED")
	@UpdateTimestamp
	private LocalDateTime modified;
    
	@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private DicPerso perso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getPerLevel() {
		return perLevel;
	}

	public void setPerLevel(int perLevel) {
		this.perLevel = perLevel;
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

	public DicPerso getPerso() {
		return perso;
	}

	public void setPerso(DicPerso perso) {
		this.perso = perso;
	}
	
}