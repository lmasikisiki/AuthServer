package com.apisec.userdetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
	@Query("SELECT u FROM Role u WHERE u.name =:name")
	List<Role> findByRoleName(@Param("name") String name);
}
