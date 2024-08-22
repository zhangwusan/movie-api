package com.api.movie.dtos.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRegisterResponse {
    private UUID id;
    private String username;
    private String email;
    private String role;
}
