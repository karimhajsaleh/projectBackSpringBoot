package com.example.projectsimple.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; 
    private String password;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    // Méthode de l’interface UserDetails : retourne les rôles de l’utilisateur au format Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Pour chaque rôle ("USER", "ADMIN"), on crée une autorité "ROLE_USER", "ROLE_ADMIN"
        return roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)
                .toList();
    }

    // Méthodes nécessaires pour que Spring Security puisse valider un utilisateur
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
