package com.mike.usermessages.controller;

import com.mike.usermessages.exception.AppError;
import com.mike.usermessages.model.User;
import com.mike.usermessages.service.SecurityUserService;
import com.mike.usermessages.service.UserService;
import com.mike.usermessages.service.dto.JwtRequestDto;
import com.mike.usermessages.service.dto.JwtResponseDto;
import com.mike.usermessages.service.dto.UserRegRequestDto;
import com.mike.usermessages.service.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class AuthController {

    private final SecurityUserService securityUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(SecurityUserService securityUserService, JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder) {
        this.securityUserService = securityUserService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDto.getUsername(),
                    jwtRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Incorrect login or password."), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = securityUserService.loadUserByUsername(jwtRequestDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestParam UserRegRequestDto userRegRequestDto) {
        if (userService.getUserByUsername(userRegRequestDto.getUsername()) != null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with this " +
                    "username already exist"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setFirstName(userRegRequestDto.getFirstName());
        user.setMiddleName(userRegRequestDto.getMiddleName());
        user.setLastName(userRegRequestDto.getLastName());
        user.setUsername(userRegRequestDto.getUsername());
        user.setEmail(userRegRequestDto.getEmail());
        user.setPassword(userRegRequestDto.getPassword());
        userService.createUser(user);
        return ResponseEntity.ok("New user created successfully.");
    }
}
