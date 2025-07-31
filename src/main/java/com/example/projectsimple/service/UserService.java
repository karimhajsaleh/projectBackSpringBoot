package com.example.projectsimple.service;

import com.example.projectsimple.dto.RegisterRequest;
import com.example.projectsimple.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}
