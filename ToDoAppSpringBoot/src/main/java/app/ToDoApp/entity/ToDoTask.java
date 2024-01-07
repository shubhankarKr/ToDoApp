package app.ToDoApp.entity;

import java.util.Date;

import app.ToDoApp.modelDTO.ToDoTaskDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "to_do_list")
public class ToDoTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private int id;

	@Column(name = "created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "last_Updated_Date")
	@Temporal(TemporalType.TIMESTAMP)

	private Date lastUpdatedDate;
	
	private String desciption;
	
	private String title;
	
	@Column(name = "user_name")
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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ToDoTaskDTO createDTO(ToDoTask toDo) {
		ToDoTaskDTO toDoDto=new ToDoTaskDTO();
		toDoDto.setCreatedDate(toDo.getCreatedDate());
		toDoDto.setDescription(toDo.getDesciption());
		toDoDto.setLastUpdatedDate(toDo.getLastUpdatedDate());
		toDoDto.setTitle(toDo.getTitle());
		toDoDto.setId(toDo.getId());
		toDoDto.setUserName(toDo.getUserName());
		return toDoDto;
	}
}
