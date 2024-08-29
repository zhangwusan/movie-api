package com.api.movie.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.mapping.UserActivityLogsMapper;
import com.api.movie.dtos.request.UserActivityLogsResquest;
import com.api.movie.dtos.response.UserActivityLogsResponse;
import com.api.movie.models.User;
import com.api.movie.models.UserActivityLogs;
import com.api.movie.service.UserActivityLogsService;
import com.api.movie.service.UserService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user-activity-logs")
public class UserActivityLogsController {

    @Autowired
    private UserActivityLogsService service;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserActivityLogsResponse>>> getAllEntity() {
        List<UserActivityLogsResponse> responses = service.getAllUserActivityLogs().stream()
                .map(UserActivityLogsMapper::toUserActivityLogsResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(responses, "Data was fetched successfully", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserActivityLogsResponse>> addEntity(
            @Valid @RequestBody UserActivityLogsResquest resquest) {
        User user = userService.getUserById(resquest.userId());
        UserActivityLogs userActivityLogs = UserActivityLogsMapper.toUserActivityLogs(resquest, user);
        return ResponseEntity.ok(new ApiResponse<>(UserActivityLogsMapper.toUserActivityLogsResponse(userActivityLogs),
                "success",
                HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserActivityLogsResponse>> getEntityById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                UserActivityLogsMapper.toUserActivityLogsResponse(service.getUserActivityLogsById(id)),
                "success",
                HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserActivityLogsResponse>> updateEntity(@PathVariable Long id,
            @RequestBody UserActivityLogsResquest resquest) {
        User user = userService.getUserById(resquest.userId());
        UserActivityLogs updated = service.updateActivityLogsById(id,
                UserActivityLogsMapper.toUserActivityLogs(resquest, user));
        return updated != null
                ? ResponseEntity.ok(new ApiResponse<>(UserActivityLogsMapper.toUserActivityLogsResponse(updated),
                        "updated",
                        HttpStatus.OK))
                : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEntity(@PathVariable Long id) {
        return service.deleteUserActivityLogs(id) ? ResponseEntity.ok(new ApiResponse<>("deleted", HttpStatus.OK))
                : ResponseEntity.noContent().build();
    }
}
