package com.mysite.mds.movie;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.mds.DataNotFoundException;
import com.mysite.mds.actor.Actor;
import com.mysite.mds.actor.ActorRepository;
import com.mysite.mds.director.Director;
import com.mysite.mds.director.DirectorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieService {

	private final MovieRepository movieRepository;
	private final DirectorRepository directorRepository;
	private final ActorRepository actorRepository;

	public Movie createMovie(MovieForm movieForm) {
		
		Movie movie = new Movie();
		movie.setTitle(movieForm.getTitle());
		movie.setContent(movieForm.getContent());

		Optional<Director> od = this.directorRepository.findByName(movieForm.getDirectorName());
		
		if (od.isPresent()) { 
			movie.setDirector(od.get());
			
		} else {
			Director newDirector = new Director();
			newDirector.setName(movieForm.getDirectorName());
			this.directorRepository.save(newDirector);
			movie.setDirector(newDirector);
		}

		Optional<Actor> oa = this.actorRepository.findByName(movieForm.getActorName());
		
		Set<Actor> actorSet = new HashSet<>();
		if (oa.isPresent()) {
			actorSet.add(oa.get());

		} else {
			Actor newActor = new Actor();
			newActor.setName(movieForm.getActorName());
			this.actorRepository.save(newActor);
			actorSet.add(newActor);
		}
		
		movie.setActorList(actorSet);
		this.movieRepository.save(movie);
		
		return movie;
	}
	
	
	
	
	
	
	public List<Movie> getList() {
		return this.movieRepository.findAll();
	}
	
	public Page<Movie> getMovieList(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return this.movieRepository.findAll(pageable);
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

	

	
	
	public void modify(Movie movie, String title, String directorName, String actorName, String content) {
		
	}
	
	public void delete(Movie movie) {
		this.movieRepository.delete(movie);
	}

}
