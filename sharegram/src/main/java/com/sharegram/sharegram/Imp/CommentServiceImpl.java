package com.sharegram.sharegram.Imp;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharegram.sharegram.models.Comment;
import com.sharegram.sharegram.models.Post;
import com.sharegram.sharegram.repo.CommentRepo;
import com.sharegram.sharegram.services.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;

	@Override
	public void saveComment(Post post, String username, String content) {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setUsername(username);
		comment.setPostedDate(new Date());
		post.setComments(comment);
		commentRepo.save(comment);
	}

}
