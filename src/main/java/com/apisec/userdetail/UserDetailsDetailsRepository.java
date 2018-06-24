package com.apisec.userdetail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserDetailsDetailsRepository extends MongoRepository<User, String> {
	User findByUsername(@Param("username") String username);
}