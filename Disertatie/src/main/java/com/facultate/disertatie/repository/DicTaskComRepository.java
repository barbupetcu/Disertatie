package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicTaskComment;

public interface DicTaskComRepository extends JpaRepository<DicTaskComment, Long> {
	
	public DicTaskComment findByTask_id(long id);
}