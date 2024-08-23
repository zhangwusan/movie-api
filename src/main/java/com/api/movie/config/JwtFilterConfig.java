package com.api.movie.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.movie.service.JwtService;
import com.api.movie.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Configuration
public class JwtFilterConfig extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        String authzHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authzHeader != null && authzHeader.startsWith("Bearer ")) {
            token = authzHeader.substring(7).trim();
            try {
                username = jwtService.extractUsername(token);            } catch (Exception e) {
                logger.error("JWT extraction failed", e);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            MyUserDetailsService userDetailsService = context.getBean(MyUserDetailsService.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
