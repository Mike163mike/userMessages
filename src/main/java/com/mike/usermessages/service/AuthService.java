package com.mike.usermessages.service;

import com.mike.usermessages.exception.AppError;
import com.mike.usermessages.model.User;
import com.mike.usermessages.service.dto.JwtResponseDto;
import com.mike.usermessages.service.dto.UserRegRequestDto;
import com.mike.usermessages.service.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUserService securityUserService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtTokenUtil jwtTokenUtil, SecurityUserService securityUserService, UserService userService,
                       AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.securityUserService = securityUserService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    public ResponseEntity<?> createAuthToken(UserRegRequestDto userRegRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegRequestDto.getUsername(),
                    userRegRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Incorrect login or password."), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = securityUserService.loadUserByUsername(userRegRequestDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    public ResponseEntity<?> createUser(UserRegRequestDto userRegRequestDto) {
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
