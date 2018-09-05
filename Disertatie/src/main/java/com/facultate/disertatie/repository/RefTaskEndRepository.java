package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.facultate.disertatie.entity.RefTaskEnd;

public interface RefTaskEndRepository extends JpaRepository<RefTaskEnd, Integer>{
	
	@Query(value = "Select * from REF_TASK_END where ?1 between max_days and min_days", nativeQuery = true)
	public RefTaskEnd findByDays (Long days);
}
