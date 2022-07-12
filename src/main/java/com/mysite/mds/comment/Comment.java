package com.mysite.mds.comment;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mysite.mds.movie.Movie;
import com.mysite.mds.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Movie movie;
	
	@Column
	private Integer stars;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
		
}
