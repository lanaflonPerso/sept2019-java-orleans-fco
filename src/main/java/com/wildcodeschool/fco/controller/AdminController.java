package com.wildcodeschool.fco.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.wildcodeschool.fco.entity.Encounter;
import com.wildcodeschool.fco.repository.EncounterRepository;

@Controller
public class AdminController {

	@Autowired
	private EncounterRepository encounterRepository;
	private String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static";
	
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
			@RequestParam ("teamName") String teamName,
			@RequestParam ("teamDivision") String teamDivision,
			@RequestParam ("matchDate") String matchDate,
			@RequestParam ("matchHour") String matchHour,
			@RequestParam ("visitorName") String visitorName,
			@RequestParam ("visitorLogo") MultipartFile visitorLogo
			) {
		//Upload du logo
		try {
			byte[] bytes = visitorLogo.getBytes();
	        Path path = Paths.get(UPLOADED_FOLDER + "/img/" + visitorLogo.getOriginalFilename());
	        Files.write(path, bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//Mise en forme de la date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    String tempDate = matchDate + " " + matchHour;
	    Date date = null;
		try {
			date = dateFormat.parse(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long time = date.getTime();
		// Remplacement du match à afficher & delete logo
		Encounter encounter = new Encounter(time, teamName, teamDivision, visitorName, "/img/" + visitorLogo.getOriginalFilename());
		Encounter encounterToDelete = encounterRepository.findTopByOrderByTimeUntilMatchAsc();
		encounterRepository.delete(encounterToDelete);
		File fileToDelete = new File(UPLOADED_FOLDER + encounterToDelete.getVisiteurLogo());
		fileToDelete.delete();
		encounterRepository.save(encounter);
		return "adminNextMatch";
	}
}
