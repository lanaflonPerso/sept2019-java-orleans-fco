package com.wildcodeschool.fco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wildcodeschool.fco.repository.MessageRepository;

@Controller
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/admin/messages")
	public String toAdminMessage(Model model) {
		model.addAttribute("messageCount", messageRepository.count());
		model.addAttribute("messages", messageRepository.findAllByOrderByDateDesc());
		return "adminMessage";
	}
	
	@GetMapping("/admin/messages/delete{id}")
	public String removeMessage(@PathVariable int id) {
		messageRepository.deleteById(id);
		return "redirect:/admin/messages";
	}
}
