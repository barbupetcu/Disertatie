package com.facultate.disertatie.service;

import com.facultate.disertatie.entity.AppUser;

public interface UserService {
    void save(AppUser user);

    AppUser findByUsername(String username);
    
}