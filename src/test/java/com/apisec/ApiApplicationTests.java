//package com.apisec;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
////
////import com.apisec.userdetails.Role;
////import com.apisec.userdetails.User;
////import com.apisec.userdetails.UserDetailsDetailsRepository;
////import com.apisec.userdetails.UserRoleRepository;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApiApplicationTests {
//	@Autowired
//	private BCryptPasswordEncoder pse;
//	@Autowired
//	private com.apisec.userdetails.UserDetailsDetailsRepository cudservice;
//	@Autowired
//	private com.apisec.userdetails.UserRoleRepository urs;
//
//	// @Test
//	public void contextLoads() {
//		com.apisec.userdetails.User u = new com.apisec.userdetails.User();
//		u.setUsername("lizo");
//		u.setPassword(pse.encode("password"));
//		u.setIsEnabled(true);
//		com.apisec.userdetails.Role r = new com.apisec.userdetails.Role();
//		r.setName("USER");
//
//		com.apisec.userdetails.Role	  r2 = new com.apisec.userdetails.Role();
//		r.setName("VIEWER");
//		r = urs.save(r);
//		r2 = urs.save(r2);
//		Set<Role> s = new HashSet<Role>();
//		s.add(r);
//		s.add(r2);
//		u.setRoles(s);
//
//		// cudservice.delete(Long.parseLong(7+""));;
//		// cudservice.save(u);
//		cudservice.findAll().forEach(e -> System.out.println(e.toString()));
//	}
//
//	// @Test
//	public void addRoles() {
//		Gson g = new Gson();
//		JsonObject o = new JsonObject();
//		User u = new User();
//		u.setIsEnabled(true);
//		u.setPassword("password");
//		u.setUsername("lele");
//		JsonArray arr = new JsonArray();
//		o.add("user", g.toJsonTree(u));
//		arr.add("USER");
//		arr.add("ADMIN");
//		o.add("roles", arr);
//
//	  System.out.print(o.getAsString());
//	}
//
//}
