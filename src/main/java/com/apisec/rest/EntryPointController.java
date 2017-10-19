package com.apisec.rest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.apisec.userdetail.UserDetailsDetailsRepository;
import com.apisec.userdetail.UserRoleRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/api")
public class EntryPointController {
	@Autowired
	private UserDetailsDetailsRepository userDetailsService;
	@Autowired
	private UserRoleRepository userRoleRepository;
	private JsonParser jsonParser = new JsonParser();
	private Gson gson = new Gson();

	@PostMapping("/user/add")
	@Procedure("application/json")
	@Consumes("application/json")
	public @ResponseBody User addUser(@RequestBody String userData) {
		User user = null;
		try {
			JsonObject jsonObject = (JsonObject) jsonParser.parse(userData);
			user = getUser(jsonObject);
			Set<String> roleNames = this.getRoles(jsonObject);
			Set<Role> roles = loadRoles(roleNames);
			user.setRoles(roles);
			user = userDetailsService.save(user);
		} catch (Exception e) {
			System.out.println("Could not save the user \n" + e.getMessage() + " \n" + userData);
			user = null;
		}
		return user;
	}

	private Set<Role> loadRoles(Set<String> roles) {
		return userRoleRepository.findAll().stream().filter(e -> roles.contains(e.getName()))
				.collect(Collectors.toSet());
	}

	private Set<String> getRoles(JsonObject jsonObject) {
		Set<String> roles = new HashSet<String>();
		if (!hasProperty(jsonObject, "roles"))
			return null;
		if (jsonObject.get("roles").isJsonArray()) {
			jsonObject.get("roles").getAsJsonArray().forEach(e -> {
				roles.add(e.getAsString());
			});
		} else {
			roles.add(jsonObject.get("roles").getAsString());
		}
		return roles;
	}

	private User getUser(JsonObject jsonObject) {
		if (!hasProperty(jsonObject, "user"))
			return null;
		return this.gson.fromJson(jsonObject.get("user"), User.class);
	}

	private boolean hasProperty(JsonObject jsonObject, String member) {
		if (jsonObject.isJsonNull() || member.isEmpty())
			return false;
		return jsonObject.has(member);
	}

}
