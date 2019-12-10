package com.wildcodeschool.fco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.fco.entity.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer>{

}
