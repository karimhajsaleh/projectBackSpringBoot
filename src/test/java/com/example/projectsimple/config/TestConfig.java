package com.example.projectsimple.config;

import com.example.projectsimple.service.ArticleService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ArticleService articleService() {
        return Mockito.mock(ArticleService.class);
    }
}
