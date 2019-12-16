package com.wildcodeschool.fco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wildcodeschool.fco.repository.PoleRepository;
import com.wildcodeschool.fco.repository.SponsorRepository;
import com.wildcodeschool.fco.repository.TeamRepository;

@Controller
public class FormationController {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PoleRepository poleRepository;
	
	@GetMapping("/pole/{poleName}")
	public String toPole(@PathVariable String poleName, Model model) {
		model.addAttribute("sponsorList", sponsorRepository.sponsorSortByPriority());
		model.addAttribute("teams", teamRepository.findByPoleName(poleName));
		model.addAttribute("pole", poleRepository.findByName(poleName));
		return "pole";
	}

}
