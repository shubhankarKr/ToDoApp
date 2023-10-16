package app.ToDoApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.ToDoApp.dao.TaskDAO;
import app.ToDoApp.modelDTO.ColorCodeDTO;
import app.ToDoApp.modelDTO.ColorUpdateModel;
import app.ToDoApp.modelDTO.ToDoTaskDTO;
import app.ToDoApp.service.TaskService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskDAO dao;

	@Override
	public ToDoTaskDTO addTask(ToDoTaskDTO ToDoTaskDTO) {
		// TODO Auto-generated method stub
		return dao.addTask(ToDoTaskDTO);
	}

	@Override
	public List<ToDoTaskDTO> getTasks() {
		// TODO Auto-generated method stub
		return dao.getTasks();
	}

	@Override
	public ToDoTaskDTO update(ToDoTaskDTO ToDoTaskDTO) {
		// TODO Auto-generated method stub
		return dao.update(ToDoTaskDTO);
	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<ToDoTaskDTO> searchByString(String inputString) {
		// TODO Auto-generated method stub
		return dao.searchByString(inputString);
	}

	@Override
	public ToDoTaskDTO getUserById(int id) {
		// TODO Auto-generated method stub
		return dao.getUserById(id);
	}

	@Override
	public List<ColorCodeDTO> getColors() {
		// TODO Auto-generated method stub
		return dao.getColors();
	}

	@Override
	public Boolean updateColor(ColorUpdateModel colorUpdateModel) {
		// TODO Auto-generated method stub
		return dao.updateColor(colorUpdateModel);
	}
}
