package com.apisec.tests;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.apisec.userdetail.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class Test {
	@Autowired
	private UserService service;

	@org.junit.Test
	public void test() {
		Set<String> s = new HashSet<>();
		s.add("ADMIN");
		service.loadRoles(s).forEach(e -> {
			System.out.println("Role>>" + e.getName() + ">>" + e.getUsers().size());
		});
		;
	}

	@org.junit.Test
	@Rollback(value = true)
	public void addRoles() {
		User u = new User();

		Role r = new Role();
		r.setName("REPORTER1");
		r.setUsers(null);
		service.saveRole(r);
	}

	@org.junit.Test
	@Rollback(value = false)
	public void createUser() {

		User u = service.findUserByUsername("onie1@gmail.com");
		Set<User> set = new HashSet<User>();
		set.add(u);
		Role r = new Role();
		r.setUsers(set);
		r.setName("REPORTER");
		service.saveRole(r);

	}
}
