package com.apisec.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apisec.MessageCollection;
import com.apisec.userdetail.Role;
import com.apisec.userdetail.User;
import com.apisec.userdetail.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EntryPointController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private JsonParser jsonParser = new JsonParser();
	private Gson gson = new Gson();

	@PostMapping("/user/addrole")
	@Produces("application/json")
	@Consumes("application/json")
	public @ResponseBody MessageCollection assignRoleToUser(@RequestBody String data) {
		MessageCollection messages = new MessageCollection();
		try {
			JsonObject jsonData = (JsonObject) jsonParser.parse(data);
			if (!this.hasProperty(jsonData, "username") | !this.hasProperty(jsonData, "roleName")) {
				throw new NullPointerException("Expected object to have both username and roleName but had one");
			}

			Role role = userService.assingRoleToUser(jsonData.get("username").getAsString(),
					jsonData.get("roleName").getAsString());

			messages.addData(role);
			messages.isSuccess(true);
		} catch (Exception e) {
			messages.addMessages(e.getMessage());
			messages.addMessages(e.getClass() + "");
		}
		return messages;
	}

	// #################### ADD USERS ###########################
	@PostMapping("/user/add")
	@Produces("application/json")
	@Consumes("application/json")
	public @ResponseBody User addUser(@RequestBody String userData) {
		User user = null;
		System.out.println("Request recieved :" + userData);
		try {
			JsonObject jsonObject = (JsonObject) jsonParser.parse(userData);
			user = getUser(jsonObject);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setIsEnabled(true);
			Set<String> roleNames = this.getRoles(jsonObject);
			Set<Role> roles = userService.loadRoles(roleNames);
			user.setRoles(roles);
			user = userService.saveUser(user);
		} catch (Exception e) {
			System.out.println("Could not save the user \n" + e.getMessage() + " \n" + userData);
			user = null;
		}
		return user;
	}

	// ################### GET EXISTING USERS - LIST #####################
	@GetMapping("/user/all")
	@Produces("application/json")
	@Consumes("application/json")
	public List<String> getUsers() {
		return userService.getUsers();
	}

	// ################### GET EXISTING USER #####################
	@PostMapping("/user/")
	@Produces("application/json")
	@Consumes("application/json")
	public User getUser(@RequestBody String username) {
		return userService.getUser(username);
	}

	// ################### GET EXISTING USER #####################
	@PostMapping("/roles/")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Role> getUserRoles(@RequestBody String username) {
		return userService.getUserRoles(username);
	}

	// ################### GET Roles #####################
	@GetMapping("/roles/all")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Role> getAllRoles() {
		return userService.getAllRoles();
	}

	// ################### CHANGE USER PASSWORD ##################
	@PostMapping("/user/changepassword")
	@Produces("application/json")
	@Consumes("application/json")
	public @ResponseBody boolean changeUserPassword(@RequestBody String userPassowrdInfo) {
		boolean passwordChanged = false;
		try {
			JsonObject jsonObject = (JsonObject) jsonParser.parse(userPassowrdInfo);
			if (!jsonObject.isJsonNull() && !jsonObject.isJsonArray()) {
				if (this.hasProperty(jsonObject, "username") && this.hasProperty(jsonObject, "password")) {
					String username = jsonObject.get("username").getAsString();
					String password = jsonObject.get("password").getAsString();
					password = passwordEncoder.encode(password);
					passwordChanged = userService.changeUserPassword(username, password);
				} else
					System.out.println("not enought informtion provided");
			} else
				System.out.println("could read request data");
		} catch (Exception e) {
			System.out.println("Could not change user password \n" + e.getMessage());
		}
		return passwordChanged;
	}

	// ################### ADD MORE ROLES ########################

	@PostMapping("/role/add")
	@Produces("application/json")
	@Consumes("application/json")
	public @ResponseBody Role addRole(@RequestBody String roleData) {
		Role role = null;
		try {
			role = gson.fromJson(roleData, Role.class);
			role.setUsers(null);
			role = userService.saveRole(role);
		} catch (Exception e) {
			System.out.println("Could not save the role \n" + e.getMessage());
			role = null;
		}
		return role;
	}

	// #################### PRIVATE METHODS ###########################
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
			throw new NullPointerException("Empty object was passed");
		return jsonObject.has(member);
	}

}
