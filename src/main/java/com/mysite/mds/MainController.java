package com.mysite.mds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "redirect:/movie/list";
	}
}
