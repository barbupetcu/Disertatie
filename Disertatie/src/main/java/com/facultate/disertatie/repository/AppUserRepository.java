package com.facultate.disertatie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.projection.DisabledUsers;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUsername(String username);
	
	public Long countByEnabledAndPerso_Dept_deptId(boolean enabled, long deptId);
	
	public List<DisabledUsers> findByEnabledAndPerso_Dept_deptId(Boolean enabled, long deptId);
}