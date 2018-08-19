package com.facultate.disertatie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.entity.AppPerso;
import com.facultate.disertatie.entity.AppRole;
import com.facultate.disertatie.entity.AppUser;
import com.facultate.disertatie.repository.AppPersoRepository;
import com.facultate.disertatie.security.JWTFilter;
import com.facultate.disertatie.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class UserController {
 
    @Autowired
    private UserServiceImpl appUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppPersoRepository appPersoRepository;
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public HashMap<String, Object> checkLogin(@RequestParam String username, @RequestParam String password) throws IOException {
    	String token = null;
        AppUser appUser = appUserRepository.findByUsername(username);
    	
    	HashMap<String, Object> tokenMap = new HashMap<String, Object>();
    	
    	List<String> roles = new ArrayList<String>();
    	
    	
    	
    	if (appUser != null && bCryptPasswordEncoder.matches(password, appUser.getPassword())) {
    		if (appUser.isEnabled()) {
    			
    			for (AppRole appRole : appUser.getRoles()) {
    	    		roles.add(appRole.getRoleName());
    	    	}
    			
    			token = Jwts.builder().setSubject(username).claim(JWTFilter.AUTHORITIES_KEY, roles).setIssuedAt(new Date())
	                    .signWith(SignatureAlgorithm.HS256, JWTFilter.TOKEN_KEY).compact();
	            tokenMap.put("token", token);
	            tokenMap.put("success", true);
	            
	            Optional<AppPerso> optional = appPersoRepository.findById(appUser.getId());
	            AppPerso appPerso = optional.get();
	            
	            //adaugam in raspuns elementele care vor fi stocate permanent in partea de client
	            tokenMap.put("username", appUser.getUsername());
	            tokenMap.put("name", appPerso.getName());
	            tokenMap.put("lastName", appPerso.getLastName());
	            tokenMap.put("dept", appPerso.getDept().getDeptId());
	            tokenMap.put("id", appUser.getId());
	            
	            Set<String> rolesText = new HashSet<String>();
	            
	            Set<AppRole> appRoles = appUser.getRoles();
	            
	            for (AppRole appRole : appRoles) {
	            	rolesText.add(appRole.getRoleName());
	            }
	            
	            tokenMap.put("roles", rolesText);
	            

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
    public HashMap<String, Object> createUser(@RequestBody Map<String, Object> params) {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	AppUser appUser = mapper.convertValue(params.get("appUser"), AppUser.class);
    	AppPerso appPerso = mapper.convertValue(params.get("appPerso"), AppPerso.class);
    	
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	response.put("success", false);
        if (appUserRepository.findByUsername(appUser.getUsername()) != null) {
        	response.put("message", "Userul deja exista in baza de date");
        } else {
    	 	appUserRepository.saveCustomRole(appUser, "ROLE_USER");
        	appPerso.setUser(appUser);
        	appPersoRepository.save(appPerso);
        	response.put("success", true);
        }             
        return response;
    }

}