package com.apisec.userdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsDetailsRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM userDetail u WHERE u.username = :username")
	public User findUserByUsername(@Param("username") String username);

	@Query("UPDATE userDetail SET password=:password WHERE username =:username ")
	@Modifying
	public boolean changedUserPassword(@Param("username") String username, @Param("password") String password);
	
	
}