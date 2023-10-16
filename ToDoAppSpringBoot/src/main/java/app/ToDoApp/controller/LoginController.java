package app.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.ToDoApp.dao.TaskUserDAO;
import app.ToDoApp.entity.TaskUser;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	TaskUserDAO taskUserDAO;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody TaskUser taskUser) {
		ResponseEntity<String> res=null;
		List<TaskUser> taskUserInDB=taskUserDAO.findByUserName(taskUser.getUserName());
		if(taskUserInDB.size()>0) {
			res=new ResponseEntity<String>(taskUser.getUserName()+" already exists",HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			try {
				taskUser.setPassword(encoder.encode(taskUser.getPassword()));
				TaskUser taskUserAfterSave=taskUserDAO.save(taskUser);
				if(taskUserAfterSave!=null) {
					res=new ResponseEntity<String>("User created successfully",HttpStatus.CREATED);
				}
			}catch(Exception e) {
				res=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return res;
	}
	
	@GetMapping("/login")
    public TaskUser getUserDetailsAfterLogin(Authentication authentication) {
        List<TaskUser> customers = taskUserDAO.findByUserName(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
