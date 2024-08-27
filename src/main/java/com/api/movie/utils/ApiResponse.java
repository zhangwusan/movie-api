package com.api.movie.utils;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
