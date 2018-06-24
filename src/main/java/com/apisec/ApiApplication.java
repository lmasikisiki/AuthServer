package com.apisec;

import com.apisec.userdetail.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.apisec.userdetail.UserRoleRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan({ "com.apisec", "com.apisec.tests.controllers" })
public class ApiApplication {

 	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=  SpringApplication.run(ApiApplication.class, args);
		PasswordEncoder passwordEncoder =ctx.getBean(PasswordEncoder.class);
		UserService userService = ctx.getBean(UserService.class);
		UserRoleRepository userRoleRepo= ctx.getBean(UserRoleRepository.class);

		//setup startup roles
		Long l= Long.parseLong("12345667");
		Role role = new Role();
		role.setName("ADMIN");
		//role.setRoleId(l);
		role.setUsers(null);
//		 userRoleRepo.save(role);
		// Add startup user
		User user =  new User();

		user.setPassword(passwordEncoder.encode("changeit"));
		user.setIsEnabled(true);
		user.setUsername("masikisiki@gmail.com");
		Set<String> roleNames =  new HashSet<>();
		roleNames.add("ADMIN");
		roleNames.add("Admin");
		roleNames.add("USER");
		Set<Role> roles =  new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
//		userService.saveUser(user);
	}
}
