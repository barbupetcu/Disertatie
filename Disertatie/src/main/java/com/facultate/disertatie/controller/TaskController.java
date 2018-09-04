package com.facultate.disertatie.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.entity.DicTask;
import com.facultate.disertatie.service.TaskService;


@RestController
public class TaskController {
 
    @Autowired
    private TaskService taskService;
    
    @RequestMapping(value = "/api/addtask", method = RequestMethod.POST)
	public HashMap<String, Object> addTask(@RequestBody DicTask task){
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	response.put("success", false);
    	
    	if(taskService.addTask(task) != null) {
    		response.put("success", true);
    	} else {
    		response.put("message", "Task-ul nu a putut fi salvat");
    	}

		return response;
	}
    
    @RequestMapping(value = "/api/gettask", method = RequestMethod.GET)
	public DicTask getTask(@RequestParam long id){
    	return taskService.getTask(id);
	}
    
    @RequestMapping(value = "/api/gettasks", method = RequestMethod.GET)
	public List<DicTask> getTasks(@RequestParam long sprintId){
    	return taskService.getTaskbySprint(sprintId);
	}

}