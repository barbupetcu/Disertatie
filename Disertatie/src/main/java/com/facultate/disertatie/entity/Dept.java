package com.facultate.disertatie.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEPT")
public class Dept {
	@Id
	@Column(name = "id", nullable = false, updatable=false)
	private Long deptId;
	
	@Column(name = "nume_dept", length=30)
	private String numeDept;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(name = "dept_manager", joinColumns = @JoinColumn(name = "dept_id"), inverseJoinColumns = @JoinColumn(name = "manager_id"))
	private DicPerso manager;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept", fetch=FetchType.LAZY)
	private Set<DicPerso> angajati;
	
	public Set<DicPerso> getAngajati() {
		
		return angajati;
	}

	public void setAngajati(Set<DicPerso> angajati) {
		this.angajati = angajati;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getNumeDept() {
		return numeDept;
	}

	public void setNumeDept(String numeDept) {
		this.numeDept = numeDept;
	}

	public DicPerso getManager() {
		return manager;
	}

	public void setManager(DicPerso manager) {
		this.manager = manager;
	}
	
}