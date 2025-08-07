package com.example.projectsimple.repository;

import com.example.projectsimple.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
// Tu peux ajouter des méthodes de recherche si besoin plus tard
}