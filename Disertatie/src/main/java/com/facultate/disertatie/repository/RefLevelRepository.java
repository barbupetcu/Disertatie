package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.RefLevel;

public interface RefLevelRepository extends JpaRepository<RefLevel, Integer>{
	public RefLevel findById (int id);
	
}
