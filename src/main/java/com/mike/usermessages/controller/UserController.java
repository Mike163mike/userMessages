package com.mike.usermessages.controller;

import com.mike.usermessages.service.UserService;
import com.mike.usermessages.service.dto.UserResponseDto;
import com.mike.usermessages.service.mapper.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API. Contains operations with users in " +
        "system.")
public class UserController {
    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    public UserController(UserService userService, UserResponseMapper userResponseMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
    }

    @Operation(summary = "Edit created users.")
    @PutMapping(value = "/edit/{user_id}")
    public ResponseEntity<?> userEdit(@PathVariable Integer user_id, @RequestBody List<String> roles) {
        return new ResponseEntity<>(userResponseMapper.map(userService.editUserRoles(user_id, roles)),
                HttpStatus.OK);
    }

    @Operation(summary = "Get all users.", description = "get all users from system.")
    @GetMapping("/all_users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userResponseMapper.toList(userService.getAllUsers());
        return ResponseEntity.ok(userResponseDtos);
    }

    @Operation(summary = "Get user by id.")
    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer user_id) {
        return ResponseEntity.ok(userResponseMapper.map(userService.getUserById(user_id)));
    }

    @Operation(summary = "Delete user by username.")
    @DeleteMapping("/delete/{username}")
    private ResponseEntity<?> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }
}
