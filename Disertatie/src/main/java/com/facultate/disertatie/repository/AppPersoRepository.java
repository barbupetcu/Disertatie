package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.AppPerso;


public interface AppPersoRepository extends JpaRepository<AppPerso, Long> {
	
	public AppPerso findByid(Long id);
}