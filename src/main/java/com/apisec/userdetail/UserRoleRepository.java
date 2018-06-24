package com.apisec.userdetail;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRoleRepository extends MongoRepository<Role, String> {
 	List<Role> findByName(String name);
}
