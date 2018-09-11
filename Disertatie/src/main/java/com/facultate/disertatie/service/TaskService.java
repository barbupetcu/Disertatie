package com.facultate.disertatie.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facultate.disertatie.entity.DicTask;
import com.facultate.disertatie.entity.DicUserLevel;
import com.facultate.disertatie.entity.RefTaskEnd;
import com.facultate.disertatie.repository.DicTaskRepository;
import com.facultate.disertatie.repository.DicUserLevelRepository;
import com.facultate.disertatie.repository.RefLevelRepository;
import com.facultate.disertatie.repository.RefTaskEndRepository;
import com.facultate.disertatie.repository.RefTaskStatusRepository;

@Service
public class TaskService {
	
	@Autowired
	private RefTaskStatusRepository refTaskStatusRepository;
    @Autowired
    private DicTaskRepository dicTaskRepository;
    
    @Autowired
    private DicUserLevelRepository dicUserLevelRepository;
    @Autowired
    private RefLevelRepository refLevelRepository;
    @Autowired
    private RefTaskEndRepository refTaskEndRepository;
    
    public List<DicUserLevel> getAllLevelDetails(long id) {
    	return dicUserLevelRepository.findByPerso_dept_deptIdOrderByTotalPointsDesc(id);
    }
    
    public DicUserLevel getLevelDetails(long id) {
    	return dicUserLevelRepository.findByPerso_id(id);
    }
    
    public List<DicTask> getUserTasks (long id){
    	return dicTaskRepository.findByUser_IdAndStatus_idNot(id, 4);
    }
    
    public List<DicTask> getDeptTasks (long id){
    	return dicTaskRepository.findByUser_dept_deptIdAndStatus_idNot(id, 4);
    }
    
    public DicTask addTask (DicTask task) {
    	
    	if (task.getTaskIteration()!=null) {
    		task.setStatus(refTaskStatusRepository.findById(2));
    	}
    	else {
    		task.setStatus(refTaskStatusRepository.findById(1));
    	}
    	
    	return dicTaskRepository.save(task);
    	
    }
    
    public DicTask getTask (long id) {
    	return dicTaskRepository.getOne(id);
    }
    
    public List<DicTask> getTaskbySprint (long sprintId){
    	return dicTaskRepository.findByTaskIteration_id(sprintId);
    }
    
    public List<DicTask> getTaskbyDept (){
    	return dicTaskRepository.findAll();
    }
    
        
    public List<DicTask> updateStatus(List<DicTask> tasks){
    	List<DicTask> updatedTasks = new ArrayList<DicTask>();
    	
    	for (DicTask task: tasks) {
    		//In cazul in care task-ul a fost trecut din statusul In Progress in Done
    		long userId = task.getUser().getId();
    		DicUserLevel userLevel = dicUserLevelRepository.findById(userId);
    		
    		//in cazul in care statusul este Done iar punctele nu au fost acordate
    		if (task.getStatus().getId() == 4 && task.getPoints() == null) {
    			//actualizam data la care a fost terminat task-ul
    			task.setEnd_date(LocalDateTime.now());
    			
    			//calculam punctele acordate in functie de mai multe criterii
    			// 1 - punctele obtinute in functie de dificultatea task-ului
    			double pct = task.getDifficulty().getQuota();
    			
    			//calculam zilele de intarziere
    			long delay = ChronoUnit.DAYS.between(LocalDateTime.now(), task.getDeadline());
    			
    			//determinam gradul de intariziere
    			RefTaskEnd refTaskEnd = refTaskEndRepository.findByDays(delay);
    			
    			task.setEnd_status(refTaskEnd);
    			
    			// 2 - ponderam numarul de puncte primite in functie gradul de intarziere
    			pct = pct * refTaskEnd.getWeight();
    			
    			// 3 - ponderam numarul de puncte in functie de nivelul angajatului care a terminat task-ul
    			
    			pct = pct * userLevel.getLevel().getWeight();
    			int pctInt = (int) pct;
    			task.setPoints((int) pctInt);
    			
    			//adaugam punctele obtinute pe task la punctele totale ale anggajatului si calculam noul nivel
    			int totalPointsAng = userLevel.getTotalPoints() + pctInt;
    			userLevel.setTotalPoints(totalPointsAng);
    			
    			//Daca angajatul a obtinut punctele necesare pentru a promova nivelul
    			if(totalPointsAng - userLevel.getLevel().getTotalPoints() >= userLevel.getLevel().getPointsForNewLevel()) {
    				//incrementam nivelul
    				userLevel.setLevel(refLevelRepository.findById(userLevel.getLevel().getId() +1));
    			}
    			
    			//calculam noul procentaj pana la urmatorul nivel
    			double procent = (userLevel.getTotalPoints() - userLevel.getLevel().getTotalPoints());
    			procent = procent / userLevel.getLevel().getPointsForNewLevel() ;
    			procent = procent * 100;
				userLevel.setPerLevel((int) procent);
				
				dicUserLevelRepository.save(userLevel);	
    			 
    		} 
    		// in cazul in care statusul task-ului a regresat din Done in altul inferior scadem punctele acordate initial
    		else if (task.getStatus().getId() != 4 && task.getPoints() != null){    			
    			//scadem punctele acordate
    			int totalPoints = userLevel.getTotalPoints() - task.getPoints();
    			userLevel.setTotalPoints(totalPoints);
    			
    			//in cazul in care numarul actual de puncte nu mai corespunde nivelului inital, scadem nivelul cu o unitate
    			if(userLevel.getTotalPoints() - userLevel.getLevel().getTotalPoints() < 0) {
    				//incrementam nivelul
    				userLevel.setLevel(refLevelRepository.findById(userLevel.getLevel().getId() - 1));
    			}
    			
    			//calculam nou procentaj pana la urmatorul nivel
    			double procent = (userLevel.getTotalPoints() - userLevel.getLevel().getTotalPoints());
    			procent = procent / userLevel.getLevel().getPointsForNewLevel() ;
    			procent = procent * 100;
				userLevel.setPerLevel((int) procent);
				
				//resetam datele specifice task-urilor terminate
				task.setEnd_date(null);
				task.setEnd_status(null);
				task.setPoints(null);
				
				dicUserLevelRepository.save(userLevel);					
    		}
    		
    		updatedTasks.add(dicTaskRepository.save(task));
    		
    	}
    	
    	return updatedTasks;
    }
    
}
