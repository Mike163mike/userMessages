package com.mike.usermessages.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @PostMapping("register")
    @Operation(summary = "Registration of new user")
    public ResponseEntity<String> userRegistration() {

        return new ResponseEntity<>("User was registered successfully.", HttpStatus.OK);
    }

    @PostMapping("login")
    @Operation(summary = "Login user")
    public ResponseEntity<String> userLogin() {

        return new ResponseEntity<>("User was logout successfully.", HttpStatus.OK);
    }

    @PostMapping("logout")
    @Operation(summary = "Logout user")
    public ResponseEntity<String> userLogout() {

        return new ResponseEntity<>("User was logout successfully.", HttpStatus.OK);
    }
}
