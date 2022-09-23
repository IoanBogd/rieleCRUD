package com.example.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.back.models.TaskModel;
import com.example.back.services.TaskServiceAPI;

@Controller
@RequestMapping("/home")
public class TaskController {

	@Autowired
	private TaskServiceAPI taskServiceAPI;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", taskServiceAPI.getAll());
		return "index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("task", new Task());
		return "save";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") Long id, Model model) {
		if (id != null && id != 0) {
			model.addAttribute("task", taskServiceAPI.get(id));
		} else {
			model.addAttribute("task", new Task());
		}
		return "save";
	}

	@PostMapping("/save")
	public String save(Task task, Model model) {
		taskServiceAPI.save(task);
		return "redirect:/home/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		taskServiceAPI.delete(id);

		return "redirect:/home/";
	}

}
    // TO DO

   /* @Scheduled("0 2 0 ? * * *")
    public void runningState


    @Scheduled("0 8 0 ? * * *")
    public void doneState  */ 
