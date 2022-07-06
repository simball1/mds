package com.mysite.mds.movie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysite.mds.comment.Comment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String title;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;

}
