package com.wildcodeschool.fco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wildcodeschool.fco.repository.MessageRepository;

@Controller
public class AdminController {

	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/admin")
	public String toAdmin(Model model) {
		model.addAttribute("messageCount", messageRepository.count());
		return "admin";
	}
}
