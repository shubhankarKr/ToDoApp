package app.ToDoApp.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import app.ToDoApp.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler=new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");
		
		http.securityContext(securityContext->securityContext.requireExplicitSave(false))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		.cors((cors)->cors.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				/*
				 * CorsConfiguration config= new CorsConfiguration();
				 * config.setAllowedOrigins(Collections.singletonList("http://localhost:63300"))
				 * ; config.setAllowedHeaders(Collections.singletonList("*"));
				 * config.setAllowedMethods(Collections.singletonList("*"));
				 * config.setAllowCredentials(true); config.setMaxAge(3600L); return config;
				 */
				
				 CorsConfiguration config = new CorsConfiguration();
                 config.setAllowedOrigins( Arrays.asList("http://localhost:4200","http://localhost:4400"));
                 config.setAllowedMethods(Collections.singletonList("*"));
              
                 config.setAllowCredentials(true);
                 config.setAllowedHeaders(Collections.singletonList("*"));
                 config.setMaxAge(3600L);
                 return config;
			}
		}))
		.csrf((csrf)->
		csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/user/register")
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
		.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
		.authorizeHttpRequests((requests) -> 
				 requests.requestMatchers("/user/register").permitAll()
//				.requestMatchers("/task/getAllTasks").permitAll()
//				.requestMatchers("/task/user").authenticated()
				.requestMatchers("/**").authenticated()
				)
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	UserCache userCache() {
	    return new NullUserCache();
	}
	
//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
//		UserDetails hello=User.withUsername("hello").password(encoder.encode("12345")).build();
//		return new InMemoryUserDetailsManager(hello);
//	}
	
//	@Bean
//	JdbcUserDetailsManager jdbcUserDetailsManager(DataSource datasource) {
//		return new JdbcUserDetailsManager(datasource);
//	}
}
