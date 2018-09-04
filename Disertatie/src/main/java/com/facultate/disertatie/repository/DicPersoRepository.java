package com.facultate.disertatie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.DicPerso;
import com.facultate.disertatie.projection.TeamUsers;


public interface DicPersoRepository extends JpaRepository<DicPerso, Long> {
	
	public DicPerso findByid(Long id);
	
	public List<TeamUsers> findByDept_deptIdAndUser_enabled(long deptId, boolean enabled);
}