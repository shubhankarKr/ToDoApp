package app.ToDoApp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.ToDoApp.entity.TaskUser;

public interface TaskUserDAO extends CrudRepository<TaskUser, Integer> {
	List<TaskUser> findByUserName(String userName);
}
