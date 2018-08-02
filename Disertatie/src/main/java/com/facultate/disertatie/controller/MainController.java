package com.facultate.disertatie.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.dao.AppRoleDAO;
import com.facultate.disertatie.dao.AppUserDAO;
import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.repository.AppUserRepository;


@RestController
public class MainController {


	@Autowired
    private AppUserDAO appUserDAO;
 
    @Autowired
    private AppUserRepository appUserRepository;


    
    @RequestMapping(value = "/api/users/dbadmin1", method = RequestMethod.POST)
    public HashMap<String, Object> getUsername (@RequestParam String username){
    	HashMap<String, Object> tokenMap = new HashMap<String, Object>();
    	
    	AppUser appUser = appUserDAO.findUserAccount(username);
    	tokenMap.put("user", appUser);
    	
    	return tokenMap;
    }
    
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public HashMap<String, Object> checkLogin(Principal principal) throws IOException {
    	HashMap<String, Object> tokenMap = new HashMap<String, Object>();
    	
    	return tokenMap;
        
       
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HashMap<String, Object> createUser(@RequestParam String username, @RequestParam String password) {
    	AppUser appUser = new AppUser();
    	appUser.setUserName(username);
    	appUser.setEncrytedPassword(password);
    	appUser.setUserId(4L);
    	
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	response.put("success", false);
        if (appUserRepository.findOneByUsername(appUser.getUserName()) != null) {
        	response.put("message", "Userul deja exista");
        } else {
        	appUserRepository.save(appUser);
        	response.put("success", true);
        }
        
        
        return response;
    }

}