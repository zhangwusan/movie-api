package com.api.movie.service.implement;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.movie.exception.UserAlreadyExistsException;
import com.api.movie.exception.UserNotFoundException;
import com.api.movie.models.User;
import com.api.movie.repositories.UserRepository;
import com.api.movie.service.JwtService;
import com.api.movie.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;

    @Override
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()
                || userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with username " + user.getUsername() + " or email " + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User does not found by email " + email));
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not found by id " + id.toString()));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User does not found by username " + username));
    }

    @Override
    public User updateUserById(UUID id, User userUpdate) {
        return userRepository.findById(id).map((user) -> {
            user.setUsername(userUpdate.getUsername());
            user.setEmail(userUpdate.getEmail());
            user.setEnabled(userUpdate.isEnabled());
            user.setUpdatedAt(new Date());
            if (!user.getPassword().equals(userUpdate.getPassword())) {
                user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
            }
            return userRepository.save(user);

        }).orElseThrow(() -> new UserNotFoundException("User does not found by id " + id.toString()));
    }

    @Override
    public boolean isValidPassword(String passwordLogin, String passwordDocument) {
        return passwordEncoder.matches(passwordLogin, passwordDocument);
    }

    @Override
    public boolean isValidEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "failure";
    }

}
