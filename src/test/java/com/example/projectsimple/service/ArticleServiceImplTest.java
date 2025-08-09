package com.example.projectsimple.service;

import com.example.projectsimple.entity.Article;
import com.example.projectsimple.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    public ArticleServiceImplTest() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    void testCreateArticle() {
        Article article = new Article(1L, "Titre", "Contenu", "Auteur");
        when(articleRepository.save(article)).thenReturn(article);

        Article saved = articleService.createArticle(article);

        assertNotNull(saved);
        assertEquals("Titre", saved.getTitle());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void testGetAllArticles() {
        List<Article> articles = Arrays.asList(
                new Article(1L, "Titre1", "Contenu1", "Auteur1"),
                new Article(2L, "Titre2", "Contenu2", "Auteur2")
        );
        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> result = articleService.getAllArticles();

        assertEquals(2, result.size());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testGetArticleById_Found() {
        Article article = new Article(1L, "Titre", "Contenu", "Auteur");
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        Article found = articleService.getArticleById(1L);

        assertNotNull(found);
        assertEquals("Titre", found.getTitle());
    }

    @Test
    void testGetArticleById_NotFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        Article found = articleService.getArticleById(1L);

        assertNull(found);
    }
}
