package com.wildcodeschool.fco.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wildcodeschool.fco.entity.Article;
import com.wildcodeschool.fco.entity.Encounter;
import com.wildcodeschool.fco.entity.Sponsor;
import com.wildcodeschool.fco.repository.ArticleRepository;
import com.wildcodeschool.fco.repository.EncounterRepository;
import com.wildcodeschool.fco.repository.SponsorRepository;

@Controller
public class IndexController {

	@Autowired
	private ArticleRepository articleRepository;
	
	List<Article> primaryArticles;
	List<Article> secondeArticles;
	List<Article> thirdArticles;
	List<Sponsor> sponsorList;
  
  @Autowired
	private SponsorRepository sponsorRepository;

	@Autowired
	private EncounterRepository encounterRepository;
	
	@GetMapping("/")
	public String toHome(Model model) {
		
		Encounter encounter = encounterRepository.findTopByOrderByTimeUntilMatchAsc();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM");
		String formatedMatchDate = formatter.format(encounter.getTimeUntilMatch());
		
		primaryArticles = articleRepository.priorityByDate(1);
		if (primaryArticles.size() > 3) {
			primaryArticles = articleRepository.priorityByDate(1).subList(0, 3);
		}
		
		secondeArticles = articleRepository.priorityByDate(2);
		if (secondeArticles.size() > 3) {
			secondeArticles = articleRepository.priorityByDate(2).subList(0, 3);
		}
		
		thirdArticles 	= articleRepository.priorityByDate(3);
		if (thirdArticles.size() > 6) {
			thirdArticles 	= articleRepository.priorityByDate(3).subList(0, 6);
		}
		
		model.addAttribute("primaryArticles", primaryArticles);
		model.addAttribute("secondeArticles", secondeArticles);
		model.addAttribute("thirdArticles",thirdArticles);
		model.addAttribute("nextMatch", encounter);
		model.addAttribute("formatedMatchDate", formatedMatchDate);
		model.addAttribute("sponsorList", sponsorRepository.sponsorSortByPriority());

		return "index";
	}
}
