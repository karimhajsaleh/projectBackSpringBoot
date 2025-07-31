package com.example.projectsimple.service;

import com.example.projectsimple.dto.RegisterRequest;
import com.example.projectsimple.entity.User;
import com.example.projectsimple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        System.out.println("Enregistrement utilisateur : karim");
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(request.getRoles())
                .build();
        System.out.println("Enregistrement utilisateur : " + user.getUsername());

        return userRepository.save(user);
    }
}
