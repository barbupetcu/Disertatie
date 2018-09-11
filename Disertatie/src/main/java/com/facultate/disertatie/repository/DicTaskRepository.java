package com.facultate.disertatie.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicTask;

public interface DicTaskRepository extends JpaRepository<DicTask, Long> {
	
	public List<DicTask> findByTaskIteration_id(long id);
	
	public DicTask findById(long id);
	
	public List<DicTask> findByUser_Dept_deptIdOrderByCreatedDesc(long id);
	
	public List<DicTask> findByUser_IdAndStatus_idNot(long id, int statusId);
	
	public List<DicTask> findByUser_dept_deptIdAndStatus_idNot(long id, int statusId);
	
}