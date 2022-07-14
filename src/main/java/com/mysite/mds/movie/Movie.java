package com.mysite.mds.movie;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mysite.mds.actor.Actor;
import com.mysite.mds.comment.Comment;
import com.mysite.mds.director.Director;

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
	
	@ManyToOne
	private Director director;
	
	@ManyToMany
	Set<Actor> actor;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column
	private Double avgStarScore;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;

	
}
