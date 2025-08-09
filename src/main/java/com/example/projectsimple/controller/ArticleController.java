package com.example.projectsimple.controller;

import com.example.projectsimple.entity.Article;
import com.example.projectsimple.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Endpoints pour la gestion des articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    @Operation(summary = "Créer un nouvel article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Article> create(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.createArticle(article));
    }

    @GetMapping
    @Operation(summary = "Lister tous les articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des articles récupérée avec succès")
    })
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article) {
        return ResponseEntity.ok(articleService.updateArticle(id, article));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Article introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
