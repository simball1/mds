package com.mysite.mds.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.mds.movie.Movie;
import com.mysite.mds.movie.MovieRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public void create(Movie movie, Integer starScore, String content) {
		Comment comment = new Comment();
		comment.setMovie(movie);
		comment.setStars(starScore);
		comment.setContent(content);
		comment.setCreateDate(LocalDateTime.now());
		this.commentRepository.save(comment);
	}
}
