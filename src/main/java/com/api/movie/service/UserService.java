package com.api.movie.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.movie.models.User;

import java.util.List;

@Service
public interface UserService {
    // Define methods for user CRUD operations
    public User createUser(User user);

    public User getUserById(UUID id);

    public User updateUserById(UUID id, User user);

    public void deleteUserById(UUID id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public boolean isValidEmail(String email);

    public boolean isValidPassword(String passwordLogin, String passwordDocument);

    public String verify(User user);

    public List<User> getAllUsers();
}
