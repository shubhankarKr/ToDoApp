package app.ToDoApp.modelDTO;

import java.util.Date;

import app.ToDoApp.entity.ToDoTask;

public class ToDoTaskDTO {
	private int id;
	private Date createdDate;
	private Date lastUpdatedDate;
	private String description;
	private String title;
	private String colourCode;
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColourCode() {
		return colourCode;
	}

	public void setColourCode(String colourCode) {
		this.colourCode = colourCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ToDoTask createEntity(ToDoTaskDTO toDoDTO) {
		ToDoTask toDo=new ToDoTask();
//		toDo.setCreatedDate(toDoDTO.getCreatedDate());
		toDo.setDesciption(toDoDTO.getDescription());
//		toDo.setLastUpdatedDate(toDoDTO.getLastUpdatedDate());
		toDo.setTitle(toDoDTO.getTitle());
		toDo.setUserName(toDoDTO.getUserName());
		return toDo;
	}
	
	

}
