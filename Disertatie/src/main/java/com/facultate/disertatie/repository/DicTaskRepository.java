package com.facultate.disertatie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicTask;

public interface DicTaskRepository extends JpaRepository<DicTask, Long> {
	
	public List<DicTask> findByTaskIteration_id(long id);
	
	public DicTask findById(long id);
	
	public List<DicTask> findByUser_Dept_deptId(long id);
	
}