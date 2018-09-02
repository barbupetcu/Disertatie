package com.facultate.disertatie.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.facultate.disertatie.entity.DicPerso;
import com.facultate.disertatie.entity.AppRole;
import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.projection.DisabledUsers;
import com.facultate.disertatie.repository.AppRoleRepository;
import com.facultate.disertatie.repository.AppUserRepository;

@Service
public class UserServiceImpl {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private AppRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveCustomRole(AppUser user, String roleName) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<AppRole> customRole = new HashSet<>();

        customRole.add(roleRepository.findByroleName(roleName));
        user.setRoles(customRole);
        DicPerso appPerso = user.getPerso();
        appPerso.setUser(user);
        user.setPerso(appPerso);
        
        userRepository.save(user);
    }
    
    public void saveAllRoles(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public AppUser findById(Long id) {
    	Optional<AppUser> optional = userRepository.findById(id);
    	AppUser appUser = optional.get();
        return appUser;
    }
    
    public AppUser editUser(AppUser appUser) {
    	AppUser temp = findById(appUser.getId());
    	appUser.setPassword(temp.getPassword());
    	return userRepository.save(appUser);
    }
    
    public void saveUser(AppUser user) {
    	userRepository.save(user);
    }
    
    public long countDisabledUser(long dept) {
    	return userRepository.countByEnabledAndPerso_Dept_deptId(false, dept);
    }
    
    public List<DisabledUsers> getDisabledUsers(Long deptId){
		return userRepository.findByEnabledAndPerso_Dept_deptId(false, deptId);
    	
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);;
    }
}
