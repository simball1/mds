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
		if (om.isPresent()) {
			return om.get();
		} else {
			throw new DataNotFoundException("movie not found");
		}
	}

	public Movie getMovie(String title) {
		Movie movie = this.movieRepository.findByTitle(title);

		if (movie != null) {
			return movie;
		} else {
			throw new DataNotFoundException("movie not found");

		}
	}

	public void updateAvgStarScore(Integer id, Double avgStarScore) {
		Optional<Movie> om = this.movieRepository.findById(id);
		if (om.isPresent()) {
			Movie movie = om.get();
			movie.setAvgStarScore(avgStarScore);
			this.movieRepository.save(movie);
		} else {
			throw new DataNotFoundException("movie not found");
		}

	}

}
