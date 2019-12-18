package com.wildcodeschool.fco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wildcodeschool.fco.entity.Article;
import com.wildcodeschool.fco.repository.ArticleRepository;


@Controller
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	private String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static";
	
	
	List<Article> listArticleByOrder;
	
	@GetMapping("/admin/articles")
	public String toAdminArticles(Model model)  {
		model.addAttribute("articles", articleRepository.findAllByOrderByDateDesc());
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
	public String postArticle( Model model,
			@RequestParam ("id") Integer id,@RequestParam ("author") String author,  
			@RequestParam ("title") String title,  @RequestParam ("content") String content, 
			@RequestParam ("division") String division,
			@RequestParam ("priority") int priority,
			@RequestParam ("picture") MultipartFile picture ) {
		

		try {
			byte[] bytes = picture.getBytes();
	        Path path = Paths.get(UPLOADED_FOLDER + "/img/" + picture.getOriginalFilename());
	        Files.write(path, bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	 	Article article =new Article(id, division, author, title, content, null , "/img/photoArticles/" + picture.getOriginalFilename(), priority);
		article.setDate(new Date());
    	articleRepository.save(article);
		model.addAttribute("article",article);
		
; 		return "redirect:/admin/articles";
	}
    
    @GetMapping("/admin/articles/delete")
    public String deleteArticles(@RequestParam Integer id) {
    	articleRepository.deleteById(id);
        return "redirect:/admin/articles";
    }
}
