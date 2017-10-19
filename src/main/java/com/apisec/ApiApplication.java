package com.apisec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apisec.userdetail.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@SpringBootApplication
@ComponentScan({ "com.apisec", "com.apisec.controllers" })
public class ApiApplication {

	public static void main(String[] args) {
		Gson g = new Gson();
		JsonObject o = new JsonObject();
		User u = new User();
		u.setIsEnabled(true);
		u.setPassword("password");
		u.setUsername("lele");
		JsonArray arr = new JsonArray();
		o.add("user", g.toJsonTree(u));
		arr.add("USER");
		arr.add("ADMIN");
		o.add("roles", arr);

		System.out.print(o.toString());
		SpringApplication.run(ApiApplication.class, args);
	}

	public void test(){
		System.out.println();
	}

}
