package app.ToDoApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.ToDoApp.dao.TaskUserDAO;
import app.ToDoApp.entity.TaskUser;

@Service
public class UserService implements UserDetailsService {
	
		@Autowired
		TaskUserDAO taskUserDAO;
	
	    public String getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        Object bo=authentication.getDetails();
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof String) {
	            return (String)principal;
	        } else {
	            return null;
	        }
	    }

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			List<TaskUser> users = taskUserDAO.findByUserName(username);
			if (users.size() == 0) {
				throw new UsernameNotFoundException("User Does not exists!");
			}
			else {
				List<GrantedAuthority> dbAuths = new ArrayList<>();
				return new User(users.get(0).getUserName(), users.get(0).getPassword(), dbAuths);
			}
		}
}
