package com.mysite.mds.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.mds.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieService {

	private final MovieRepository movieRepository; 
	
	public List<Movie> getList() {
		return this.movieRepository.findAll();
	}

	
	public Movie getMovie(Integer id) {
		Optional<Movie> om = this.movieRepository.findById(id);
		if(om.isPresent()) {
			return om.get();
		}
		else {
			throw new DataNotFoundException("movie not found");
		}
	}
}
