package com.mysite.mds.movie;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieService {

	private final MovieRepository movieRepository;
	
	public List<Movie> getList() {
		return this.movieRepository.findAll();
	}

}
