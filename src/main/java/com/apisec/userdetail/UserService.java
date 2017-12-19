package com.apisec.userdetail;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDetailsDetailsRepository userDetailsService;
	@Autowired
	private UserRoleRepository userRoleRepository;

	public User findUserByUsername(String username) {
		return userDetailsService.findUserByUsername(username);
	}

	public Set<Role> loadRoles(Set<String> roleNames) {
		Set<Role> fullRoles = new HashSet<>();
		for (String roleName : roleNames) {
			List<Role> roles = userRoleRepository.findByRoleName(roleName);
			if (!roles.isEmpty()) {
				Role role = roles.get(0);
				if (!fullRoles.contains(role)) {
					fullRoles.add(role);
				}
			}
		}
		return fullRoles;
	}

	public User saveUser(User user) {
		return userDetailsService.save(user);
	}

	public Role saveRole(Role role) {
		return userRoleRepository.save(role);
	}

	public boolean changeUserPassword(String username, String password) {
		return userDetailsService.changedUserPassword(username, password);
	}

	public List<String> getUsers() {
		//@formatter:off
		return userDetailsService
				.findAll()
				.stream()
				.map(user -> user.getUsername())
				.collect(Collectors.toList());
		//@formatter:on	 	
	}

	public List<Role> getUserRoles(String username) {
		return userRoleRepository.findByRoleName(username);
	}

	public List<Role> getAllRoles() {
		return userRoleRepository.findAll();
	}

	public User getUser(String username) {
		return userDetailsService.findUserByUsername(username);
	}

	public Role assingRoleToUser(String username, String roleName) {

		User user = userDetailsService.findUserByUsername(username);
		if (user == null)
			throw new NullPointerException("No user found with that username :" + username);

		List<Role> roles = userRoleRepository.findByRoleName(roleName);
		if (roles == null | roles.isEmpty())
			throw new NullPointerException("No role found with that rolename :" + roleName);

		Role role = roles.get(0);
		Collection<User> users = role.getUsers();
		System.out.println("Count existing matching users"+users.stream().filter(u -> u.getUsername().trim().equals(username.trim())).count());
		if (users.stream().filter(u -> u.getUsername().trim().equals(username.trim())).count() > 0)
			throw new IllegalArgumentException(
					String.format("User with username %s already has role: %s ", username, role.getName()));

		users.add(user);
		role.setUsers(users);
		return userRoleRepository.save(role);
	}
}