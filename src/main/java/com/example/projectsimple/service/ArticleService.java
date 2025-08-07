package com.example.projectsimple.service;

import com.example.projectsimple.entity.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Article updateArticle(Long id, Article updated);
    void deleteArticle(Long id);
}