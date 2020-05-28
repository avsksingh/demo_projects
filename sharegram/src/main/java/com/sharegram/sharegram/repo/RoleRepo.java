package com.sharegram.sharegram.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharegram.sharegram.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	
	public Role findRoleByName(String name);

}
