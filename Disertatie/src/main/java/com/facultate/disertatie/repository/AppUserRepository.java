package com.facultate.disertatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.facultate.disertatie.entity.AppUser;



public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findOneByUsername(String username);
}