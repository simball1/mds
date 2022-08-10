package com.mysite.mds.comment;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.mds.movie.Movie;
import com.mysite.mds.movie.MovieService;
import com.mysite.mds.user.SiteUser;
import com.mysite.mds.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {

	private final MovieService movieService;
	private final CommentService commentService;
	private final UserService userService;
	
	@PostMapping("/create/{id}")
	public String create(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal) {
		
		Movie movie = this.movieService.getMovie(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("movie", movie);
			return "movie_detail";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		Integer stars = Integer.parseInt(commentForm.getStars());
		
		this.commentService.create(movie, stars, commentForm.getContent(), siteUser);
		this.movieService.updateAvgStarScore(id, getAvgStarScore(id));
		
		return String.format("redirect:/movie/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modify(CommentForm commentForm, @PathVariable("id") Integer id, Principal principal) {
		Comment comment = this.commentService.getComment(id);
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		commentForm.setContent(comment.getContent());
		commentForm.setStars(comment.getStars().toString());
		return "comment_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@Valid CommentForm commentForm, BindingResult bindingResult,
			@PathVariable("id") Integer id, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "comment_form";
		}
		Comment comment = this.commentService.getComment(id);
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.commentService.modify(comment, 
				commentForm.getContent(), Integer.parseInt(commentForm.getStars()));
		
		int movieId = comment.getMovie().getId();
		this.movieService.updateAvgStarScore(movieId, getAvgStarScore(movieId));
		
		return String.format("redirect:/movie/detail/%s", movieId);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, Principal principal) {
		Comment comment = this.commentService.getComment(id);
		if(!comment.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		this.commentService.delete(comment);
		
		int movieId = comment.getMovie().getId();
		this.movieService.updateAvgStarScore(movieId, getAvgStarScore(movieId));
		
		return String.format("redirect:/movie/detail/%s", movieId);
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
