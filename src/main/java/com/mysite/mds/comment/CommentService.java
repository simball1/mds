package com.mysite.mds.comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.mds.DataNotFoundException;
import com.mysite.mds.movie.Movie;
import com.mysite.mds.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public void create(Movie movie, Integer starScore, String content, SiteUser author) {
		Comment comment = new Comment();
		comment.setMovie(movie);
		comment.setStars(starScore);
		comment.setContent(content);
		comment.setAuthor(author);
		comment.setCreateDate(LocalDateTime.now());
		this.commentRepository.save(comment);
	}

	public Comment getComment(Integer id) {
		Optional<Comment> comment= this.commentRepository.findById(id);
		if(comment.isPresent()) {
			return comment.get();
		}
		else {
			throw new DataNotFoundException("comment not found");
		}
	}
	
	public void modify(Comment comment, String content, Integer starScore) {
		comment.setContent(content);
		comment.setStars(starScore);
		this.commentRepository.save(comment);
	}
	
	public void delete(Comment comment) {
		this.commentRepository.delete(comment);
	}
}
