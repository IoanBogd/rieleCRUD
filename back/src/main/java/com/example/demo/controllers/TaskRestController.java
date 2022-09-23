package com.example.back.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.models.TaskModel;
import com.example.back.service.TaskServiceAPI;

@RestController
@RequestMapping(value = "/task/")
@CrossOrigin("*")
public class TaskRestController {

	@Autowired
	private TaskServiceAPI taskServiceAPI;

	@GetMapping(value = "/all")
	public List<Task> getAll() {
		return taskServiceAPI.getAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public Task find(@PathVariable Long id) {
		return taskServiceAPI.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Task> save(@RequestBody @Valid Task task) {
		Task obj = taskServiceAPI.save(task);
		return new ResponseEntity<Task>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Task> delete(@PathVariable Long id) {
		Task task = taskServiceAPI.get(id);
		if (task != null) {
			taskServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

}

