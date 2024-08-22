package com.api.movie.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.api.movie.models.User;

@Service
public interface UserService extends UserDetailsService {
    // Define methods for user CRUD operations
    public User createUser(User user);
    public User getUserById(UUID id);
    public User updateUserById(UUID id, User user);
    public void deleteUserById(UUID id);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String passwordLogin, String passwordDocument);
}
