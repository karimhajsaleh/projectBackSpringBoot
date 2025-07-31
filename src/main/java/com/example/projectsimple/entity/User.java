package com.example.projectsimple.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity // Indique que cette classe est une entité JPA (sera mappée à une table dans la base de données)
@Table(name = "users") // Le nom de la table dans la base de données sera "users"
@Data // Lombok génère automatiquement les getters, setters, toString, equals, hashCode
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs
@Builder // Permet d’utiliser le design pattern Builder pour créer un objet User facilement
public class User implements UserDetails {
    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // L’ID sera généré automatiquement par la base (auto-incrément)
    private Long id;

    private String username; // Nom d’utilisateur
    private String password; // Mot de passe (chiffré)
    private String email;    // Adresse e-mail

    @ElementCollection(fetch = FetchType.EAGER) // Permet de stocker une collection simple (ici, une liste de rôles) dans une table secondaire
    private Set<String> roles = new HashSet<>(); // Ensemble des rôles de l’utilisateur, comme "USER" ou "ADMIN"

    // Méthode de l’interface UserDetails : retourne les rôles de l’utilisateur au format Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Pour chaque rôle ("USER", "ADMIN"), on crée une autorité "ROLE_USER", "ROLE_ADMIN"
        return roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)
                .toList();
    }

    // Méthodes nécessaires pour que Spring Security puisse valider un utilisateur
    @Override public boolean isAccountNonExpired() { return true; }      // Le compte n'est jamais expiré
    @Override public boolean isAccountNonLocked() { return true; }       // Le compte n'est jamais verrouillé
    @Override public boolean isCredentialsNonExpired() { return true; }  // Les informations d’identification ne sont jamais expirées
    @Override public boolean isEnabled() { return true; }                // L’utilisateur est toujours actif

}
