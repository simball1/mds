package com.mysite.mds;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.mds.comment.Comment;
import com.mysite.mds.comment.CommentRepository;
import com.mysite.mds.movie.Movie;
import com.mysite.mds.movie.MovieRepository;

@SpringBootTest
class MdsApplicationTests {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Test
	void testJpa() {
		Optional<Movie> om = this.movieRepository.findById(2);
		assertTrue(om.isPresent());
		Movie movie = om.get();
		
		Comment comment = new Comment();
		comment.setMovie(movie);
		comment.setStars(5);
		comment.setContent("명작입니다.");
		comment.setCreatDate(LocalDateTime.now());
		this.commentRepository.save(comment);
		
	}

}
