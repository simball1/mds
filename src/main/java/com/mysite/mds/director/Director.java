package com.mysite.mds.director;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysite.mds.movie.Movie;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
	private List<Movie> movieList;
	
}
