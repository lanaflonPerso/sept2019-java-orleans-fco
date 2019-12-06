package com.wildcodeschool.fco.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wildcodeschool.fco.entity.Encounter;
import com.wildcodeschool.fco.repository.EncounterRepository;

@Controller
public class IndexController {

	@Autowired
	private EncounterRepository encounterRepository;
	
	@GetMapping("/")
	public String toHome(Model model) {
		Encounter encounter = encounterRepository.findTopByOrderByTimeUntilMatchAsc();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM");
		String formatedMatchDate = formatter.format(encounter.getTimeUntilMatch());
		model.addAttribute("nextMatch", encounter);
		model.addAttribute("formatedMatchDate", formatedMatchDate);
		System.out.println(new Date().getTime());
		return "index";
	}
}
