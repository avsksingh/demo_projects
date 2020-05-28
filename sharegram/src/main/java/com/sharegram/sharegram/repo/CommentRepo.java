package com.sharegram.sharegram.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharegram.sharegram.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{

}
