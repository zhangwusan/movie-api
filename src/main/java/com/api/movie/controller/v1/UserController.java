package com.api.movie.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.mapping.UserMapper;
import com.api.movie.dtos.request.UserRequest;
import com.api.movie.dtos.response.UserResponse;
import com.api.movie.models.User;
import com.api.movie.service.UserService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    // creat user
    @RequestMapping
    public ResponseEntity<ApiResponse<UserResponse>> addUser(@Valid @RequestBody UserRequest request) {
        try {
            User user = service.createUser(UserMapper.toUser(request));
            UserResponse userResponse = UserMapper.toUserResponse(user);
            return ResponseEntity.ok(new ApiResponse<>(userResponse, "User created successfully", HttpStatus.CREATED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    // get all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        try {
            List<UserResponse> response = service.getAllUsers().stream().map(UserMapper::toUserResponse).toList();
            return ResponseEntity.ok(new ApiResponse<>(response, "fetching users is successful", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }

    // update users
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@Valid @PathVariable UUID id,
            @Valid @RequestBody UserRequest request) {
        try {
            User user = service.updateUserById(id, UserMapper.toUser(request));
            UserResponse userResponse = UserMapper.toUserResponse(user);
            return ResponseEntity.ok(new ApiResponse<>(userResponse, "User updated successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable UUID id) {
        try {
            service.deleteUserById(id);
            return ResponseEntity.ok(new ApiResponse<>("User was deleted successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID id) {
        try {
            User user = service.getUserById(id);
            UserResponse userResponse = UserMapper.toUserResponse(user);
            return ResponseEntity.ok(new ApiResponse<>(userResponse, "User fetched successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
