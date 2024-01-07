package app.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.ToDoApp.modelDTO.ToDoTaskDTO;
import app.ToDoApp.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	TaskService service; 
	
	@PostMapping(path = "/add")
	public ToDoTaskDTO addTask(@RequestBody ToDoTaskDTO ToDoTaskDTO) {
		return service.addTask(ToDoTaskDTO);
	}

	@GetMapping("/getAllTasks")
	public List<ToDoTaskDTO> getTasks() {
		return service.getTasks();
	}

	@PutMapping("/update")
	public ToDoTaskDTO update(@RequestBody ToDoTaskDTO ToDoTaskDTO) {
		return service.update(ToDoTaskDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	public Boolean delete(@PathVariable int id) {
		return service.delete(id);
	}
	
	@GetMapping("/search/{inputString}")
	public List<ToDoTaskDTO> searchByString(@PathVariable String inputString ){
		return service.searchByString(inputString);
	}
	
	@GetMapping("/{id}")
	public ToDoTaskDTO getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
}
