package app.ToDoApp.dao;

import java.util.List;
import app.ToDoApp.modelDTO.ColorCodeDTO;
import app.ToDoApp.modelDTO.ColorUpdateModel;
import app.ToDoApp.modelDTO.ToDoTaskDTO;

public interface TaskDAO {
	public ToDoTaskDTO addTask(ToDoTaskDTO ToDoTaskDTO);
	public List<ToDoTaskDTO> getTasks();
	public ToDoTaskDTO update(ToDoTaskDTO ToDoTaskDTO);
	public Boolean delete(int id);
	public List<ToDoTaskDTO> searchByString( String inputString );
	public ToDoTaskDTO getUserById(int id);
	public List<ColorCodeDTO> getColors();
	public Boolean updateColor(ColorUpdateModel colorUpdateModel);
}
