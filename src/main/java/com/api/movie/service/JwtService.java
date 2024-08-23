package com.api.movie.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    public String generateToken(String username);
    public boolean validateToken(String token, UserDetails userDetails);
    public String extractUsername(String token);
}
