package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicPerso;


public interface AppPersoRepository extends JpaRepository<DicPerso, Long> {
	
	public DicPerso findByid(Long id);
}