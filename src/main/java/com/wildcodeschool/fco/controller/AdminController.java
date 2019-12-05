package com.wildcodeschool.fco.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.fco.entity.Encounter;
import com.wildcodeschool.fco.repository.EncounterRepository;

@Controller
public class AdminController {

	@Autowired
	private EncounterRepository encounterRepository;
	
	@GetMapping("/admin")
	public String toAdmin() {
		return "admin";
	}
	
	@GetMapping("/admin/next-match")
	public String toAdminNextMatch(Model model) {
		model.addAttribute("encounter", encounterRepository.findTopByOrderByTimeUntilMatchAsc());
		return "adminNextMatch";
	}
	
	@PostMapping("/admin/next-match")
	public String updateNextMatch(
			@RequestParam String teamName,
			@RequestParam String teamDivision,
			@RequestParam String matchDate,
			@RequestParam String matchHour,
			@RequestParam String visitorName,
			@RequestParam String visitorLogo
			) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    String tempDate = matchDate + " " + matchHour;
	    Date date = null;
		try {
			date = dateFormat.parse(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long time = date.getTime();
		Encounter encounter = new Encounter(time, teamName, teamDivision, visitorName, visitorLogo);
		encounterRepository.delete(encounterRepository.findTopByOrderByTimeUntilMatchAsc());
		encounterRepository.save(encounter);
		return "adminNextMatch";
	}
}
