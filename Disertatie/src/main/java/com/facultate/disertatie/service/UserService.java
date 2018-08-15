package com.facultate.disertatie.service;

import com.facultate.disertatie.entity.AppUser;

public interface UserService {
	void saveCustomRole(AppUser user, String roleId);
	
    void saveAllRoles(AppUser user);

    AppUser findByUsername(String username);
    
}