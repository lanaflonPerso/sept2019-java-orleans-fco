package com.wildcodeschool.fco.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wildcodeschool.fco.entity.Sponsor;
import com.wildcodeschool.fco.repository.MessageRepository;
import com.wildcodeschool.fco.repository.SponsorRepository;

@Controller
public class AdminSponsorController {
	public String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static";

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("/admin/sponsor")
	public String toSponsorPage(Model model) {
		model.addAttribute("messageCount", messageRepository.count());
		model.addAttribute("sponsorList", sponsorRepository.sponsorSortByPriority());
		return "adminSponsor";

	}
	
	@GetMapping("/admin/sponsor/delete{id}")
	public String removeSponsor(@PathVariable Integer id) {
		Sponsor sponsorToDelete = sponsorRepository.findById(id).get();
		File fileToDelete = new File(UPLOADED_FOLDER + sponsorToDelete.getUrlPhoto());
		fileToDelete.delete();
		sponsorRepository.delete(sponsorToDelete);
		return "redirect:/admin/sponsor";
	}
	
	@GetMapping("/admin/sponsor/update")
	public String updateSponsor(Model model, @RequestParam(required = false) Integer id) {
        Sponsor sponsor = new Sponsor();
        if (id != null) {
        	Optional<Sponsor> optionalSponsor = sponsorRepository.findById(id);
        	if (optionalSponsor.isPresent()) {
        		sponsor = optionalSponsor.get();
        	}
        }
        model.addAttribute("sponsor", sponsor);
		return "adminSponsorUpdate";
	}
	
	@PostMapping("/admin/sponsor/update")
	public String postSponsor(
			@RequestParam ("id") Integer id,
			@RequestParam ("name") String name,
			@RequestParam ("urlPageSponsor") String sponsorLink,
			@RequestParam ("priority") Integer priority,
			@RequestParam ("urlPhoto") MultipartFile sponsorLogo
			) {
		
		try {
			byte[] bytes = sponsorLogo.getBytes();
	        Path path = Paths.get(UPLOADED_FOLDER + "/img/photoSponsor/" + sponsorLogo.getOriginalFilename());
	        Files.write(path, bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (id != null) {
			Sponsor sponsorToDelete = sponsorRepository.findById(id).get();
			File fileToDelete = new File(UPLOADED_FOLDER + sponsorToDelete.getUrlPhoto());
			fileToDelete.delete();
			sponsorRepository.delete(sponsorToDelete);
		}
		Sponsor sponsor = new Sponsor(id, name, sponsorLink, "/img/photoSponsor/" + sponsorLogo.getOriginalFilename(), priority);
		sponsorRepository.save(sponsor);
		
		return "redirect:/admin/sponsor";
	}
	
}
