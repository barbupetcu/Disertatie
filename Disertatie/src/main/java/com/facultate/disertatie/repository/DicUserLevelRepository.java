package com.facultate.disertatie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicUserLevel;

public interface DicUserLevelRepository extends JpaRepository<DicUserLevel, Long> {
	
	public DicUserLevel findById(long id);
	
	public DicUserLevel findByPerso_id(long id);
	
	public List<DicUserLevel> findByPerso_dept_deptIdOrderByTotalPointsDesc(long id);
	
}