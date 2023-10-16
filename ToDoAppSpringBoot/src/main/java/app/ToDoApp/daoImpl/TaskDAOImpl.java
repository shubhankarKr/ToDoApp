package app.ToDoApp.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import app.ToDoApp.dao.TaskDAO;
import app.ToDoApp.entity.ColorCode;
import app.ToDoApp.entity.ToDoTask;
import app.ToDoApp.modelDTO.ColorCodeDTO;
import app.ToDoApp.modelDTO.ColorUpdateModel;
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
//		UserDetails currentUser=userService.getCurrentUser();
//		ToDoTaskDTO.setUserName(currentUser.getUsername());
		ToDoTask toDo=ToDoTaskDTO.createEntity(ToDoTaskDTO);
		Date date=new Date();
		toDo.setCreatedDate(date);
		ColorCode color= entityManager.find(ColorCode.class, 1);
		toDo.setColor(color);
		entityManager.persist(toDo);
		ToDoTaskDTO dto=toDo.createDTO(toDo);
		return dto;
	}

	@Override  
	public List<ToDoTaskDTO> getTasks() {
		Query q=entityManager.createQuery("select t from ToDoTask t order by t.id desc");
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
			do1.setColor(null);
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

	@Override
	public List<ColorCodeDTO> getColors() {
		List<ColorCodeDTO> res=new ArrayList<>();
		List<ColorCode> entities= entityManager.createQuery("select c from ColorCode c").getResultList();
		entities.forEach(entity->{
			ColorCodeDTO dto=new ColorCodeDTO();
					dto.setColorCode(entity.getColorCode());
					dto.setColorId(entity.getColorId());
					res.add(dto);
		});
		return res;
	}

	@Override
	public Boolean updateColor(ColorUpdateModel colorUpdateModel) {
		ColorCode colorEntity = entityManager.find(ColorCode.class, colorUpdateModel.getColorId());
		if (colorEntity != null) {
			ToDoTask toDoEntity = entityManager.find(ToDoTask.class, colorUpdateModel.getTaskId());
			if (toDoEntity != null) {
				toDoEntity.setColor(colorEntity);
			}
			return true;
		}
		return false;
	}
}
