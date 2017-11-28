package com.apisec.userdetail;

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

	public User getUser(String username) {
		return userDetailsService.findUserByUsername(username);
	}
}
