package com.sharegram.sharegram.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sharegram.sharegram.models.ApUser;
import com.sharegram.sharegram.models.Role;

public interface AccountService {
	
	public ApUser saveUser(String name, String username, String email);

	public ApUser findByUsername(String username);

	public ApUser findByEmail(String userEmail);

	public List<ApUser> userList();

	public Role findUserRoleByName(String string);

	public Role saveRole(Role role);

	public void updateUserPassword(ApUser apUser, String newpassword);
	
	public ApUser updateUser(ApUser user, HashMap<String, String> request);

	public ApUser simpleSaveUser(ApUser user);

	public ApUser findUserById(Long id);
	
	public void deleteUser(ApUser apUser);

	public void resetPassword(ApUser user);

	public List<ApUser> getUsersListByUsername(String username);

	public String saveUserImage(MultipartFile multipartFile, Long userImageId);


}
