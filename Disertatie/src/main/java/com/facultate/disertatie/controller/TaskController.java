package com.facultate.disertatie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultate.disertatie.entity.DicTask;
import com.facultate.disertatie.entity.DicTaskComment;
import com.facultate.disertatie.entity.DicTaskIteration;
import com.facultate.disertatie.entity.DicUserLevel;
import com.facultate.disertatie.repository.DicTaskComRepository;
import com.facultate.disertatie.repository.DicTaskIterationRepository;
import com.facultate.disertatie.service.TaskService;


@RestController
public class TaskController {
 
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private DicTaskComRepository dicTaskComRepository;
    
    @Autowired
    private DicTaskIterationRepository dicTaskIterationRepository;
    
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
    
    
    @RequestMapping(value = "/api/sendtasks", method = RequestMethod.PUT)
	public List<DicTask> sendTasks(@RequestBody List<DicTask> tasks){
    	return taskService.updateStatus(tasks);
	}
    
    @RequestMapping(value = "/api/gettasksByDept", method = RequestMethod.GET)
   	public List<DicTask> getTasksByDept(){
       	return taskService.getTaskbyDept();
   	}
    
    @RequestMapping(value = "/api/addcomment", method = RequestMethod.POST)
   	public DicTaskComment sendComments(@RequestBody DicTaskComment comment){
       	return dicTaskComRepository.save(comment);
   	}
    
    @RequestMapping(value = "/api/commentsbytask", method = RequestMethod.GET)
   	public List<DicTaskComment> sendComments(@RequestParam long taskId){
       	return dicTaskComRepository.findByTask_id(taskId);
   	}
    
    @RequestMapping(value = "/api/loadHome", method = RequestMethod.GET)
   	public HashMap<String, Object> loadHome(@RequestParam long id){
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	DicUserLevel currentUser = taskService.getLevelDetails(id);
    	List<DicTask> myTasks = taskService.getUserTasks(id);
    	
    	response.put("currentUser", currentUser);
    	response.put("myTasks", myTasks);
    	response.put("countTasks", myTasks.size());
    	
    	return response;
   	}
    
    @RequestMapping(value = "/api/senditeration", method = RequestMethod.PUT)
	public DicTaskIteration sendIteration(@RequestBody DicTaskIteration iteration){
    	return dicTaskIterationRepository.save(iteration);
	}
    
    @RequestMapping(value = "/api/loadHomeManager", method = RequestMethod.GET)
   	public HashMap<String, Object> loadHomeManager(@RequestParam long id){
    	HashMap<String, Object> response = new HashMap<String, Object>();

    	List<DicTask> myTasks = taskService.getDeptTasks(id);
    	List<DicUserLevel> deptUsers = taskService.getAllLevelDetails(id);
    	
    	response.put("myTasks", myTasks);
    	response.put("countTasks", myTasks.size());
    	response.put("deptUsers", deptUsers);
    	
    	return response;
   	}
    
    
       

}