package com.sharegram.sharegram.services;

import com.sharegram.sharegram.models.Comment;
import com.sharegram.sharegram.models.Post;

public interface CommentService {

	public void saveComment(Post post, String username, String content);
}
