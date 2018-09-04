package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicUserLevel;

public interface DicUserLevelRepository extends JpaRepository<DicUserLevel, Long> {
	
	public DicUserLevel findById(long id);
	
}