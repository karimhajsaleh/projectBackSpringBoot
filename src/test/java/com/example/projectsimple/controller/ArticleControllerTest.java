package com.example.projectsimple.controller;

import com.example.projectsimple.config.TestConfig;
import com.example.projectsimple.entity.Article;
import com.example.projectsimple.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ArticleController.class)
@Import(TestConfig.class) // configuration pour mocker ArticleService
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleService; // sera un mock injecté via TestConfig

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testGetAllArticles() throws Exception {
        when(articleService.getAllArticles()).thenReturn(Arrays.asList(
                new Article(1L, "Titre1", "Contenu1", "Auteur1"),
                new Article(2L, "Titre2", "Contenu2", "Auteur2")
        ));

        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Titre1"));
    }

    @Test
    void testCreateArticle() throws Exception {
        Article newArticle = new Article(null, "Titre Nouveau", "Contenu Nouveau", "AuteurX");
        Article savedArticle = new Article(3L, "Titre Nouveau", "Contenu Nouveau", "AuteurX");

        when(articleService.createArticle(any(Article.class))).thenReturn(savedArticle);

        mockMvc.perform(post("/api/articles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newArticle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.title").value("Titre Nouveau"));
    }
}
