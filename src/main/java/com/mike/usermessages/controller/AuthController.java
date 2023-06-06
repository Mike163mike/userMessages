package com.mike.usermessages.controller;

import com.mike.usermessages.exception.AppError;
import com.mike.usermessages.service.SecurityUserService;
import com.mike.usermessages.service.dto.JwtRequestDto;
import com.mike.usermessages.service.dto.JwtResponseDto;
import com.mike.usermessages.service.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final SecurityUserService securityUserService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(SecurityUserService securityUserService, JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager) {
        this.securityUserService = securityUserService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
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
}
