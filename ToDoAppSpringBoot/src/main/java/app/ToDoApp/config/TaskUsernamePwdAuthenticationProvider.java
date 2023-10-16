package app.ToDoApp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.ToDoApp.dao.TaskUserDAO;
import app.ToDoApp.entity.TaskUser;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TaskUserDAO taskUserDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<TaskUser> taskUser = taskUserDAO.findByUserName(username);
        if (taskUser.size() > 0) {
            if (passwordEncoder.matches(pwd, taskUser.get(0).getPassword())) {
            	System.out.println("successful for user "+authentication.getName());
            	 List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            	 grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
                return new UsernamePasswordAuthenticationToken(username, pwd,grantedAuthorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
