package com.facultate.disertatie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.entity.AppRole;
import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.security.JWTFilter;
import com.facultate.disertatie.service.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class MainController {
 
    @Autowired
    private UserServiceImpl appUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /*
    @RequestMapping(value = "/api/users/dbadmin1", method = RequestMethod.POST)
    public HashMap<String, Object> getUsername (@RequestParam String username){
    	HashMap<String, Object> tokenMap = new HashMap<String, Object>();
    	
    	AppUser appUser = appUserDAO.findUserAccount(username);
    	tokenMap.put("user", appUser);
    	
    	return tokenMap;
    }*/
    
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public HashMap<String, Object> checkLogin(@RequestParam String username, @RequestParam String password) throws IOException {
    	String token = null;
        AppUser appUser = appUserRepository.findByUsername(username);
    	
    	HashMap<String, Object> tokenMap = new HashMap<String, Object>();
    	
    	List<String> roles = new ArrayList<String>();
    	
    	for (AppRole appRole : appUser.getRoles()) {
    		roles.add(appRole.getRoleName());
    	}
    	
    	if (appUser != null && bCryptPasswordEncoder.matches(password, appUser.getPassword())) {
    		if (appUser.isEnabled()) {
    			token = Jwts.builder().setSubject(username).claim(JWTFilter.AUTHORITIES_KEY, roles).setIssuedAt(new Date())
	                    .signWith(SignatureAlgorithm.HS256, JWTFilter.TOKEN_KEY).compact();
	            tokenMap.put("token", token);
	            tokenMap.put("success", true);
    		}
    		else {
    			tokenMap.put("message", "Contul nu este inca activat.");
    		}

        } else {
            tokenMap.put("message", "Credentialele introduse nu sunt corecte!");
  
        }
    	
    	return tokenMap;
        
       
    }
    
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HashMap<String, Object> createUser(@RequestParam String username, @RequestParam String password) {
    	AppUser appUser = new AppUser();
    	appUser.setUsername(username);
    	appUser.setPassword(password);
    	
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	response.put("success", false);
        if (appUserRepository.findByUsername(appUser.getUsername()) != null) {
        	response.put("message", "Userul deja exista");
        } else {
        	appUserRepository.save(appUser);
        	response.put("success", true);
        }
        
        
        return response;
    }

}