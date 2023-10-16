package app.ToDoApp.service;

import java.util.List;

import app.ToDoApp.modelDTO.ColorCodeDTO;
import app.ToDoApp.modelDTO.ColorUpdateModel;
import app.ToDoApp.modelDTO.ToDoTaskDTO;

public interface TaskService {
	public ToDoTaskDTO addTask(ToDoTaskDTO toDoDTO);
	public List<ToDoTaskDTO> getTasks();
	public ToDoTaskDTO update(ToDoTaskDTO toDoDTO);
	public Boolean delete(int id);
	public List<ToDoTaskDTO> searchByString( String inputString );
	public ToDoTaskDTO getUserById(int id);
	public List<ColorCodeDTO> getColors();
	public Boolean updateColor(ColorUpdateModel colorUpdateModel);
}
