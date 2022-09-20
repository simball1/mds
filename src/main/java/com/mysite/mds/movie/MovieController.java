package com.mysite.mds.movie;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.mds.comment.CommentForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/movie")
@Controller
public class MovieController {

	private final MovieService movieService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/create")
	public String create(MovieForm movieForm) {
		return "movie_form";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/create")
	public String create(@Valid MovieForm movieForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "movie_form";
		}
		this.movieService.createMovie(movieForm);
		return "redirect:/movie/list";
	}
	
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Movie> paging = this.movieService.getMovieList(page);
		model.addAttribute("paging", paging);
		return "movie_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
		Movie movie = this.movieService.getMovie(id);
		model.addAttribute("movie", movie);
		return "movie_detail";
	}
	
	
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modify(MovieForm movieForm, @PathVariable("id") Integer id, Principal principal) {
		Movie movie = this.movieService.getMovie(id);
		if(!principal.getName().equals("simball1")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		movieForm.setTitle(movie.getTitle());
		movieForm.setDirectorName(movie.getDirector().getName());
		movieForm.setContent(movie.getContent());
		
		return "movie_form";	
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@Valid MovieForm movieForm, BindingResult bindingResult , 
			@PathVariable("id") Integer id, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "movie_form";
		}
		Movie movie = this.movieService.getMovie(id);
		
		return "";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Principal principal) {
		Movie movie = this.movieService.getMovie(id);
		if(!principal.getName().equals("simball1")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		this.movieService.delete(movie);
		return "redirect:/";
	}
	
}
