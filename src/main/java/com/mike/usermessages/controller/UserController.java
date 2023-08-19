package com.mike.usermessages.controller;

import com.mike.usermessages.exception.AppException;
import com.mike.usermessages.service.UserService;
import com.mike.usermessages.mapper.dto.UserResponseDto;
import com.mike.usermessages.mapper.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "The User API. Contains operations with users in " +
        "system.")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserResponseMapper userResponseMapper;

    @Operation(summary = "Set new list of roles for earlier created users.")
    @PutMapping(value = "/{username}")
    public ResponseEntity<?> userEdit(@PathVariable String username, @RequestBody List<String> roles) {
        return new ResponseEntity<>(userResponseMapper.map(userService.editUserRoles(username, roles)),
                HttpStatus.OK);
    }

    @Operation(summary = "Get all users.", description = "Get all users from system.")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userResponseMapper.toList(userService.getAllUsers());
        return ResponseEntity.ok(userResponseDtos);
    }

    @Operation(summary = "Get user by id.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userResponseMapper.map(userService.getUserById(id)));
    }

    @Operation(summary = "Delete user by username.")
    @DeleteMapping("/{username}")
    private ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (userService.deleteUser(username) == 1) {
            return ResponseEntity.ok(String.format("User %s was deleted successfully.", username));
        }
        return ResponseEntity.badRequest().body(new AppException("Something went wrong. Try again."));
    }
}
