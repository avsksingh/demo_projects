package com.sharegram.sharegram.Imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sharegram.sharegram.models.ApUser;
import com.sharegram.sharegram.models.Role;
import com.sharegram.sharegram.models.UserRole;
import com.sharegram.sharegram.repo.ApUserRepo;
import com.sharegram.sharegram.repo.RoleRepo;
import com.sharegram.sharegram.services.AccountService;
import com.sharegram.sharegram.utility.Constants;
import com.sharegram.sharegram.utility.EmailConstructor;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ApUserRepo apUserRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private EmailConstructor emailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Transactional
	public ApUser saveUser(String name, String username, String email) {
		String password = RandomStringUtils.randomAlphanumeric(10);
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		ApUser appUser = new ApUser();
		appUser.setPassword(encryptedPassword);
		appUser.setName(name);
		appUser.setUsername(username);
		appUser.setEmail(email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(appUser, accountService.findUserRoleByName("USER")));
		appUser.setUserRoles(userRoles);
		
		apUserRepo.save(appUser);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Constants.TEMP_USER.toPath());
			String fileName = appUser.getId() + ".png";
			Path path = Paths.get(Constants.USER_FOLDER + fileName);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mailSender.send(emailConstructor.constructNewUserEmail(appUser, password));
		return appUser;
	}

	@Override
	public void updateUserPassword(ApUser appUser, String newpassword) {
		String encryptedPassword = bCryptPasswordEncoder.encode(newpassword);
		appUser.setPassword(encryptedPassword);
		apUserRepo.save(appUser);
		mailSender.send(emailConstructor.constructResetPasswordEmail(appUser, newpassword));
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public ApUser findByUsername(String username) {
		return apUserRepo.findByUsername(username);
	}

	@Override
	public ApUser findByEmail(String userEmail) {
		return apUserRepo.findByEmail(userEmail);
	}

	@Override
	public List<ApUser> userList() {
		return apUserRepo.findAll();
	}

	@Override
	public Role findUserRoleByName(String name) {
		return roleRepo.findRoleByName(name);
	}

	@Override
	public ApUser simpleSaveUser(ApUser user) {
		apUserRepo.save(user);
		mailSender.send(emailConstructor.constructUpdateUserProfileEmail(user));
		return user;

	}

	@Override
	public ApUser updateUser(ApUser user, HashMap<String, String> request) {
		String name = request.get("name");
		// String username = request.get("username");
		String email = request.get("email");
		String bio = request.get("bio");
		user.setName(name);
		// appUser.setUsername(username);
		user.setEmail(email);
		user.setBio(bio);
		apUserRepo.save(user);
		mailSender.send(emailConstructor.constructUpdateUserProfileEmail(user));
		return user;

	}

	@Override
	public ApUser findUserById(Long id) {
		return apUserRepo.findUserById(id);
	}

	@Override
	public void deleteUser(ApUser appUser) {
		apUserRepo.delete(appUser);

	}

	@Override
	public void resetPassword(ApUser user) {
		String password = RandomStringUtils.randomAlphanumeric(10);
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		user.setPassword(encryptedPassword);
		apUserRepo.save(user);
		mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));

	}

	@Override
	public List<ApUser> getUsersListByUsername(String username) {
		return apUserRepo.findByUsernameContaining(username);
	}

	@Override
	public String saveUserImage(MultipartFile multipartFile, Long userImageId) {
		/*
		 * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
		 * request; Iterator<String> it = multipartRequest.getFileNames(); MultipartFile
		 * multipartFile = multipartRequest.getFile(it.next());
		 */
		byte[] bytes;
		try {
			Files.deleteIfExists(Paths.get(Constants.USER_FOLDER + "/" + userImageId + ".png"));
			bytes = multipartFile.getBytes();
			Path path = Paths.get(Constants.USER_FOLDER + userImageId + ".png");
			Files.write(path, bytes);
			return "User picture saved to server";
		} catch (IOException e) {
			return "User picture Saved";
		}
	}

}
