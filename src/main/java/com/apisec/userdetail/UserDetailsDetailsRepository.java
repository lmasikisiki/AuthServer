package com.apisec.userdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsDetailsRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM userDetail u WHERE u.username = :username")
	public User findUserByUsername(@Param("username") String username);
}
