package com.facultate.disertatie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facultate.disertatie.entity.DicTask;
import com.facultate.disertatie.repository.DicPersoRepository;
import com.facultate.disertatie.repository.DicTaskRepository;

@Service
public class TaskService {
    @Autowired
    private DicTaskRepository dicTaskRepository;
    
    public DicTask addTask (DicTask task) {
    	return dicTaskRepository.save(task);
    }
    
    public DicTask getTask (long id) {
    	return dicTaskRepository.getOne(id);
    }
    
    public List<DicTask> getTaskbySprint (long sprintId){
    	return dicTaskRepository.findByTaskIteration_id(sprintId);
    }
    
}
