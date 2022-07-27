package com.mysite.mds.comment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.mds.movie.Movie;
import com.mysite.mds.movie.MovieService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {

	private final MovieService movieService;

	private final CommentService commentService;
	
	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult bindingResult) {
		
		Movie movie = this.movieService.getMovie(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("movie", movie);
			return "movie_detail";
		}
		Integer stars = Integer.parseInt(commentForm.getStars());
		this.commentService.create(movie, stars, commentForm.getContent());
		this.movieService.updateAvgStarScore(id, getAvgStarScore(id));
		return String.format("redirect:/movie/detail/%s", id);
	}
	
	
	public double getAvgStarScore(Integer id) {
		
		Movie movie = this.movieService.getMovie(id);
		List<Comment> commentList = movie.getCommentList();
		
		int totalScore = 0;
		
		for(Comment comment : commentList) {
			totalScore += comment.getStars();
		}
		
		return (double) totalScore / commentList.size();
		
	}
}
