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

    public void setAll(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
