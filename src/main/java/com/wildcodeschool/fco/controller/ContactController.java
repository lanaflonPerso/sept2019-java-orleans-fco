package com.wildcodeschool.fco.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wildcodeschool.fco.entity.Message;
import com.wildcodeschool.fco.repository.MessageRepository;
import com.wildcodeschool.fco.repository.SponsorRepository;

@Controller
public class ContactController {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/contact")
	public String toCatalog(Model model) {
		model.addAttribute("sponsorList", sponsorRepository.sponsorSortByPriority());
		return "contact";
	}
	
	@PostMapping("/contact")
	public String getMessage(@ModelAttribute Message message, Model model) {
		message.setDate(new Date());
		messageRepository.save(message);
		return "contact";
	}

}
