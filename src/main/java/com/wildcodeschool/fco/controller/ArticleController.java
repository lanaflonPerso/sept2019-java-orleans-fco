package com.wildcodeschool.fco.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.fco.entity.Article;
import com.wildcodeschool.fco.repository.ArticleRepository;


@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/admin/articles")
	public String toAdminArticles(Model model)  {
		model.addAttribute("articles", articleRepository.findAll());
		return "adminArticles";
	}
	
	@GetMapping("/admin/article")
	public String toArticle(Model model ,@RequestParam(required = false) Integer id) {
	 	Article article =new Article();
    	if (id != null) {
    		Optional<Article> optionalArticle = articleRepository.findById(id);
    		if (optionalArticle.isPresent()) {
    			article = optionalArticle.get();
    		}
    	}
		model.addAttribute("article", article);
		return "adminArticle";
	}
	
	
    @PostMapping("/admin/article")
    public String postArticle(@ModelAttribute Article article) {
    	article.setDate(new Date());
    	articleRepository.save(article);
        return "redirect:/admin/articles";
    }
    
    @GetMapping("/admin/articles/delete")
    public String deleteArticles(@RequestParam Integer id) {
    	articleRepository.deleteById(id);
        return "redirect:/admin/articles";
    }
}
