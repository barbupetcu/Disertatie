package com.facultate.disertatie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REF_LEVEL")
public class RefLevel{
	@Id
	@Column(name="LEVEL_ID", nullable = false, updatable=false)
	private int id;
	
	@Column(name="TOTAL_POINTS")
	private int totalPoints;
	
	@Column(name="POINTS_FOR_NEW_LEVEL")
	private int pointsForNewLevel;
	
	@Column(name="WEIGHT")
	private double weight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getPointsForNewLevel() {
		return pointsForNewLevel;
	}

	public void setPointsForNewLevel(int pointsForNewLevel) {
		this.pointsForNewLevel = pointsForNewLevel;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}