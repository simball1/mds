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

	public void createMovie(String title, String directorName, String actorName, String content) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setContent(content);

		Optional<Director> od = this.directorRepository.findByName(directorName);
		if (od.isPresent()) { // 이미 해당 감독이 등록되어 있을 경우
			movie.setDirector(od.get());
		} else { // 새로 등록되는 감독일 경우
			Director newDirector = new Director();
			newDirector.setName(directorName);
			this.directorRepository.save(newDirector);
			movie.setDirector(newDirector);
		}

		Optional<Actor> oa = this.actorRepository.findByName(actorName);
		Set<Actor> actorSet = new HashSet<>();
		if (oa.isPresent()) {
			actorSet.add(oa.get());

		} else {
			Actor newActor = new Actor();
			newActor.setName(actorName);
			this.actorRepository.save(newActor);
			actorSet.add(newActor);
		}
		movie.setActorList(actorSet);
		this.movieRepository.save(movie);
	}

	public Page<Movie> getMovieList(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return this.movieRepository.findAll(pageable);
	}
	
	public void delete(Movie movie) {
		this.movieRepository.delete(movie);
	}

}
