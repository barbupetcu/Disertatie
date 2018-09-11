package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.RefTaskStatus;

public interface RefTaskStatusRepository extends JpaRepository<RefTaskStatus, Integer>{
	
		public RefTaskStatus findById (int id);
}
