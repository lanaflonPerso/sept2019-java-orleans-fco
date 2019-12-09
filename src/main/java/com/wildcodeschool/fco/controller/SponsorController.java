package com.wildcodeschool.fco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wildcodeschool.fco.entity.Sponsor;
import com.wildcodeschool.fco.repository.SponsorRepository;

@Controller
public class SponsorController {
	public String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/photoSponsor";
	private String uploadDirectoryForDelete = System.getProperty("user.dir") + "/src/main/resources/static/";
	private int time = (int) (Math.random() * ((6000 - 1000) + 1)) + 1000;
	@Autowired
	private SponsorRepository repository;

	List<Sponsor> sponsorList;

	@GetMapping("/s")
	public String sponsorHome(Model model) {
		sponsorList = repository.sponsorSortByPriority();
		model.addAttribute("sponsorList", sponsorList);
		return "fragment/sponsor";

	}

	@GetMapping("/admin/sponsor")
	public String sponsorPage(Model model) {

		sponsorList = repository.sponsorSortByPriority();
		System.out.println(sponsorList.size());
		model.addAttribute("sponsorUpdated", new Sponsor(" ", " ", " ", 0));
		model.addAttribute("sponsorList", sponsorList);
		return "sponsorPage";

	}

	@PostMapping("/admin/addSponsor")
	public String addSponsor(Model model, @RequestParam int priority, @RequestParam String name,
			@RequestParam MultipartFile photo, @RequestParam String urlPageSponsor) {
		if (photo.getOriginalFilename().length() < 40) {
			Path fileNameAndPath = Paths.get(uploadDirectory, photo.getOriginalFilename());

			try {
				Files.write(fileNameAndPath, photo.getBytes());
				Thread.sleep(this.time);
			} catch (IOException e) {
				System.out.println(e);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		repository.save(new Sponsor(name, urlPageSponsor, "/img/photoSponsor/" + photo.getOriginalFilename(), priority));
		sponsorList = repository.sponsorSortByPriority();
		model.addAttribute("sponsorUpdated", new Sponsor(" ", " ", " ", 0));
		model.addAttribute("sponsorList", sponsorList);

		return "sponsorPage";
	}

	@PostMapping("/admin/deleteSponsor")
	public String deletSponsor(Model model, @RequestParam Long sponsorId) {
		Path fileNameAndPath = Paths.get(uploadDirectoryForDelete, repository.getOne(sponsorId).getUrlPhoto());
		try {
			Files.delete(fileNameAndPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.delete(repository.getOne(sponsorId));
		sponsorList = repository.sponsorSortByPriority();
		model.addAttribute("sponsorUpdated", new Sponsor(" ", " ", " ", 0));
		model.addAttribute("sponsorList", sponsorList);

		return "sponsorPage";
	}

	@PostMapping("/admin/retrieveSponsor")
	public String retrieveSponsor(Model model, @RequestParam Long sponsorId) {

		model.addAttribute("sponsorUpdated", repository.getOne(sponsorId));
		sponsorList = repository.sponsorSortByPriority();
		model.addAttribute("sponsorList", sponsorList);

		return "sponsorPage";
	}

	@PostMapping("/admin/updatSponsor")
	public String updatSponsor(Model model, @RequestParam int priority, @RequestParam String name,
			@RequestParam MultipartFile photo, @RequestParam String urlPageSponsor, @RequestParam Long sponsorId) {
		Path fileNameAndPathOne = Paths.get(uploadDirectoryForDelete, repository.getOne(sponsorId).getUrlPhoto());
		try {
			Files.delete(fileNameAndPathOne);

		} catch (IOException e) {
			e.printStackTrace();
		}

		Path fileNameAndPath = Paths.get(uploadDirectory, photo.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, photo.getBytes());
			Thread.sleep(this.time);
		} catch (IOException e) {
			System.out.println(e);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Sponsor s = new Sponsor(name, urlPageSponsor, "/img/photoSponsor/" + photo.getOriginalFilename(), priority);
		s.setId(sponsorId);
		repository.save(s);
		sponsorList = repository.sponsorSortByPriority();
		model.addAttribute("sponsorUpdated", new Sponsor(" ", " ", " ", 0));
		model.addAttribute("sponsorList", sponsorList);

		return "sponsorPage";
	}

}
