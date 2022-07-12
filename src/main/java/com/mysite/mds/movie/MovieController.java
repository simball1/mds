package com.mysite.mds.movie;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/movie")
@Controller
public class MovieController {

	private final MovieService movieService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Movie> movieList = this.movieService.getList();
		model.addAttribute("movieList", movieList);
		return "movie_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Movie movie = this.movieService.getMovie(id);
		model.addAttribute("movie", movie);
		return "movie_detail";
	}
	
	@GetMapping("/create")
	public String create() {
		return "movie_form";
	}
}
