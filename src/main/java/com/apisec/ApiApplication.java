package com.apisec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.apisec.userdetail.UserRoleRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@SpringBootApplication
@ComponentScan({ "com.apisec", "com.apisec.tests.controllers" })
public class ApiApplication {
	@Autowired
	private static UserRoleRepository userRoleRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		// Add startup role
		Role role = new Role();
		role.setName("ADMIN");
		role.setUsers(null);
		userRoleRepo.save(role);

	}

}
