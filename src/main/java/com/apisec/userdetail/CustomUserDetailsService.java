package com.apisec.userdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDetailsDetailsRepository userDetailsDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDetailsDetailsRepository.findByUsername(username);
 		CustomUserDetails userDetails = new CustomUserDetails(user);
		return userDetails;
	}

}
