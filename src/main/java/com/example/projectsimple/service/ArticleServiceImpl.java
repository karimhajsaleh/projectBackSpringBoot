package com.example.projectsimple.service;

import com.example.projectsimple.entity.Article;
import com.example.projectsimple.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article updateArticle(Long id, Article updated) {
        Article existing = getArticleById(id);
        if (existing == null) return null;
        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        return articleRepository.save(existing);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}