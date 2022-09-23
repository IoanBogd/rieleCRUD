package com.example.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.back.commons.GenericServiceIMPL;
import com.example.back.repositories.TaskRepository;
import com.example.back.models.TaskModel;
import com.example.back.services.TaskServiceAPI;

@Service
public class TaskServiceIMPL extends GenericServiceIMPL<Task, Long> implements TaskServiceAPI {

	@Autowired
	private TaskRepository TaskRepository;
	
	@Override
	public CrudRepository<Task, Long> getDao() {
		return TaskRepository;
	}

}