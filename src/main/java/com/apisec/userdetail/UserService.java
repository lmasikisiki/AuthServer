package com.apisec.userdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
	@Autowired
	private UserDetailsDetailsRepository userDetailsService;
	@Autowired
	private UserRoleRepository userRoleRepository;

	public User findUserByUsername(String username) {
		return userDetailsService.findByUsername(username);
	}

	public Set<Role> loadRoles(Set<String> roleNames) {
		Set<Role> fullRoles = new HashSet<>();
		for (String roleName : roleNames) {
			List<Role> roles = userRoleRepository.findByName(roleName);
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
		//return userDetailsService.changedUserPassword(username, password);
		return false;
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
		return userRoleRepository.findByName(username);
	}

	public List<Role> getAllRoles() {
		return userRoleRepository.findAll();
	}

	public User getUser(String username) {
		return userDetailsService.findByUsername(username);
	}
}
