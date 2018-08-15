package com.facultate.disertatie.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.facultate.disertatie.entity.AppRole;
import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.repository.AppRoleRepository;
import com.facultate.disertatie.repository.AppUserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private AppRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveCustomRole(AppUser user, String roleName) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<AppRole> customRole = new HashSet<>();

        customRole.add(roleRepository.findByroleName(roleName));
        user.setRoles(customRole);
        userRepository.save(user);
    }
    
    @Override
    public void saveAllRoles(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
