package com.foodkind.backend.controller;

import com.foodkind.backend.dto.ApiResponse;
import com.foodkind.backend.dto.UpdateUserRequest;
import com.foodkind.backend.dto.UserDto;
import com.foodkind.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * GET /api/users
     * Retrieve all users. Requires ADMIN role.
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        log.info("Request to get all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }

    /**
     * GET /api/users/{id}
     * Retrieve a specific user by ID. Accessible by ADMIN or the user themselves.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userAuthorizationService.isOwner(authentication, #id)")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        log.info("Request to get user with ID: {}", id);
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }

    /**
     * PUT /api/users/{id}
     * Update a user. Accessible by ADMIN or the user themselves.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userAuthorizationService.isOwner(authentication, #id)")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        log.info("Request to update user with ID: {}", id);
        UserDto updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedUser));
    }

    /**
     * DELETE /api/users/{id}
     * Delete a user. Requires ADMIN role.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        log.info("Request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }
}
