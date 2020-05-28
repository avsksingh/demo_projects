package com.sharegram.sharegram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sharegram.sharegram.models.ApUser;

public interface ApUserRepo extends JpaRepository<ApUser, Long> {
	
	public ApUser findByUsername(String username);

	public ApUser findByEmail(String userEmail);
		
	@Query("SELECT apUser FROM ApUser apUser WHERE apUser.id=:x")
	public ApUser findUserById(@ Param("x")Long id);

	public List<ApUser> findByUsernameContaining(String username);

}
