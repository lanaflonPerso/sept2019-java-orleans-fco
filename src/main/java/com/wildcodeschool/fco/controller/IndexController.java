package com.wildcodeschool.fco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	
	@GetMapping("/")
	public String toHome() {
		return "index";
	}
}
