package com.sharegram.sharegram.services;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sharegram.sharegram.models.ApUser;
import com.sharegram.sharegram.models.Post;

public interface PostService {
	public Post savePost(ApUser user, HashMap<String, String> request, String postImageName);

	public List<Post> postList();

	public Post getPostById(Long id);

	public List<Post> findPostByUsername(String username);

	public Post deletePost(Post post);
	
	public String savePostImage(MultipartFile multipartFile, String fileName);
}
