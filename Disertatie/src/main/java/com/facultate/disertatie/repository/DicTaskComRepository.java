package com.facultate.disertatie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicTaskComment;

public interface DicTaskComRepository extends JpaRepository<DicTaskComment, Long> {
	
	public List<DicTaskComment> findByTask_id(long id);
	
}