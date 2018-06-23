package com.apisec.userdetail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

//import org.springframework.data.jpa.repository.Query;

public interface UserDetailsDetailsRepository extends MongoRepository<User, Long> {
	//@Query("SELECT u FROM userDetail u WHERE u.username = :username")
	public User findByUsername(@Param("username") String username);

	//@Query("UPDATE userDetail SET password=:password WHERE username =:username ")
	//@Modifying
	//public boolean changedUserPassword(@Param("username") String username, @Param("password") String password);
	
	
}