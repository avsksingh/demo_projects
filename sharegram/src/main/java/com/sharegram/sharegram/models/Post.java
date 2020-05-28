package com.sharegram.sharegram.models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Post implements Serializable {
	
	private static final long serialVersionUID = 3234567L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "text")
	private String caption;
	private String location;
	private int likes;
	private Date postedDate;
	private long userImageid;
	private String username;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private List<Comment> commentList;
	
	public Post() {}

	public Post(Long id, String name, String caption, String location, int likes, Date postedDate,
			Integer userImageid, List<Comment> commentList) {
		super();
		this.id = id;
		this.name = name;
		this.caption = caption;
		this.location = location;
		this.username = username;
		this.likes = likes;
		this.postedDate = postedDate;
		this.userImageid = userImageid;
		this.commentList = commentList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public long getUserImageid() {
		return userImageid;
	}

	public void setUserImageid(long userImageid) {
		this.userImageid = userImageid;
	}

	public Stream<Comment> getCommentList() {
		if (commentList != null) {
			return commentList.stream().sorted(Comparator.comparing(Comment::getPostedDate));
		}
		return null;
	}

	@JsonIgnore
	public void setComments(Comment comment) {
		if (comment != null) {
			this.commentList.add(comment);
		}
	}
	
	

}
