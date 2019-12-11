package com.wildcodeschool.fco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wildcodeschool.fco.entity.Message;
import com.wildcodeschool.fco.repository.MessageRepository;

@Controller
public class ContactController {

	
	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/contact")
	public String toCatalog() {
		return "contact";
	}
	
	@PostMapping("/contact")
	public String getMessage(@ModelAttribute Message message, Model model) {
		messageRepository.save(message);
		return "contact";
	}

}
