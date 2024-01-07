package app.ToDoApp.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.ToDoApp.dao.TaskDAO;
import app.ToDoApp.entity.ToDoTask;
import app.ToDoApp.modelDTO.ToDoTaskDTO;
import app.ToDoApp.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class TaskDAOImpl implements TaskDAO{
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	UserService userService;

	
	@Override
	public ToDoTaskDTO addTask(ToDoTaskDTO ToDoTaskDTO) {
		ToDoTaskDTO.setUserName(userService.getCurrentUser());
		ToDoTask toDo=ToDoTaskDTO.createEntity(ToDoTaskDTO);
		Date date=new Date();
		toDo.setCreatedDate(date);
		entityManager.persist(toDo);
		ToDoTaskDTO dto=toDo.createDTO(toDo);
		return dto;
	}

	@Override  
	public List<ToDoTaskDTO> getTasks() {
		Query q=entityManager.createQuery("select t from ToDoTask t where t.userName = :userName order by t.id desc");
		q.setParameter("userName", userService.getCurrentUser());
		List<ToDoTask> toDoList=q.getResultList();
		List<ToDoTaskDTO> list=new ArrayList<>();
		for (ToDoTask toDo : toDoList) {
			ToDoTaskDTO doDTO=new ToDoTaskDTO();
			ToDoTaskDTO dto=toDo.createDTO(toDo);
			list.add(dto);
		}
		return list;
	}

	@Override
	public ToDoTaskDTO update(ToDoTaskDTO ToDoTaskDTO) {
		ToDoTask do1=entityManager.find(ToDoTask.class, ToDoTaskDTO.getId());
		do1.setDesciption(ToDoTaskDTO.getDescription());
		do1.setLastUpdatedDate(new Date());
		do1.setTitle(ToDoTaskDTO.getTitle());
		return do1.createDTO(do1);
	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		ToDoTask do1=entityManager.find(ToDoTask.class, id);
		if(do1 != null) {
			entityManager.remove(do1);
		}
		return true;
	}

	@Override
	public List<ToDoTaskDTO> searchByString(String inputString) {
		// TODO Auto-generated method stub
		List<ToDoTaskDTO> res=new ArrayList<>();
		Query query=entityManager.createQuery("select t from ToDoTask t where t.title like :inputString");
		query.setParameter("inputString","%"+inputString+"%");
		List<ToDoTask> data=query.getResultList();
		if(!data.isEmpty()) {
			for (ToDoTask toDo : data) {
				ToDoTaskDTO dto= toDo.createDTO(toDo);
				res.add(dto);
			}
		}
		return res;
	}

	@Override
	public ToDoTaskDTO getUserById(int id) {
		// TODO Auto-generated method stub
		ToDoTask entity=entityManager.find(ToDoTask.class, id);
		if(entity != null) {
			return entity.createDTO(entity);
		}
		else {
			return null;
		}
	}

}
