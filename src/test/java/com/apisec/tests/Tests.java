package com.apisec.tests;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Tests {
	private JsonParser jsonParser = new JsonParser();
	private User user = null;
	private Gson gson = new Gson();

	public Tests() {
		String userData = "{\"user\":{\"username\":\"lele\",\"password\":\"password\",\"isEnabled\":true},\"roles\":[\"USER\",\"ADMIN\"]}";
		JsonObject jsonObject = (JsonObject) jsonParser.parse(userData);
		user = getUser(jsonObject);
		Set<String> roleNames = this.getRoles(jsonObject);
		//Set<Role> roles = loadRoles(roleNames);
		// user.setRoles(roles);
		// user = userDetailsService.save(user);
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

	public static void main(String[] args) {
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setName("USER");
		roles.add(role);
		User user = new User();
		user.setUsername("test");
		user.setPassword("password");
		user.setRoles(roles);
		Gson g = new Gson();
		System.out.println(g.toJson(user));
		new Tests();
	}
}
