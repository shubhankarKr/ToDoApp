package app.ToDoApp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	public ResponseEntity<HashMap<String,String>> registerUser(@RequestBody TaskUser taskUser) {
		HashMap<String, String> outputType= new HashMap<>();
		ResponseEntity<HashMap<String, String>> res=null;
		List<TaskUser> taskUserInDB=taskUserDAO.findByUserName(taskUser.getUserName());
		if(taskUserInDB.size()>0) {
			outputType.put("errorMessage", taskUser.getUserName()+" already exists");
			res=new ResponseEntity<HashMap<String,String>>(outputType, HttpStatus.BAD_REQUEST);
		}
		else {
			try {
				taskUser.setPassword(encoder.encode(taskUser.getPassword()));
				TaskUser taskUserAfterSave=taskUserDAO.save(taskUser);
				outputType.put("successMessage", taskUserAfterSave.getUserName()+" created successfully");
				if(taskUserAfterSave!=null) {
					res = new ResponseEntity<HashMap<String,String>>(outputType, HttpStatus.CREATED);
				}
			}catch(Exception e) {
				res=new ResponseEntity<HashMap<String,String>>(outputType,HttpStatus.INTERNAL_SERVER_ERROR);
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
