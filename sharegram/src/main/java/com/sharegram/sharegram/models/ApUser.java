package com.sharegram.sharegram.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ApUser implements Serializable {
	
	private static final long serialVersionUID = 1234567L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private long id;
	private String name;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String email;
	
	@Column(columnDefinition = "text")
	private String bio;
	private Date createDate;
	
	@OneToMany(mappedBy = "apUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles =  new HashSet<>();
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "likedPostappUser_id")
//	private List<Post> likedPost;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> post;
	
	
	
	
	
	public ApUser() {}



	public ApUser(Long id, String name, String username, String password, String email, String bio, Date createDate,
			Set<UserRole> userRoles, List<Post> post) {
		
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.bio = bio;
		this.createDate = createDate;
		this.userRoles = userRoles;
		this.post = post;
		//this.likedPost = likedpost;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Set<UserRole> getUserRoles() {
		return userRoles;
	}



	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}



	public List<Post> getPost() {
		return post;
	}



	public void setPost(Post post) {
		this.post.add(post);
	}



//	public List<Post> getLikedPost() {
//		return likedPost;
//	}
//
//
//
//	public void setLikedPost(Post likedPost) {
//		this.likedPost.add(likedPost);
//	}
//	
	
}
