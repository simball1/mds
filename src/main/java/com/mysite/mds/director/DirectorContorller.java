package com.mysite.mds.director;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/director")
@Controller
public class DirectorContorller {

	private final DirectorService directorService;
	
	@GetMapping("/create")
	public String create(DirectorForm directorForm) {
		return "director_form";
	}
	
	@PostMapping("/create")
	public String create(@Valid DirectorForm directorForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "director_form";
		}
		this.directorService.create(directorForm.getName());
		return "redirect:/movie/list";
	}
}
