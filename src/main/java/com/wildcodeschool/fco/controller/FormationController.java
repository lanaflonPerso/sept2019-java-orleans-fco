package com.wildcodeschool.fco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wildcodeschool.fco.repository.SponsorRepository;
import com.wildcodeschool.fco.repository.TeamRepository;

@Controller
public class FormationController {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/formation")
	public String toSeniors(Model model) {
		model.addAttribute("sponsorList", sponsorRepository.sponsorSortByPriority());
		model.addAttribute("teams", teamRepository.findByPoleId(2));
		model.addAttribute("classActive","active");
		return "formation";
	}

}
