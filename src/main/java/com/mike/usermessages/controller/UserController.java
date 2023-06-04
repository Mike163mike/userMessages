package com.mike.usermessages.controller;

import com.mike.usermessages.model.Role;
import com.mike.usermessages.model.User;
import com.mike.usermessages.service.RoleService;
import com.mike.usermessages.service.UserService;
import com.mike.usermessages.service.dto.UserResponseDto;
import com.mike.usermessages.service.mapper.RoleMapper;
import com.mike.usermessages.service.mapper.UserRegRequestMapper;
import com.mike.usermessages.service.mapper.UserRequestMapper;
import com.mike.usermessages.service.mapper.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;
    private final UserRegRequestMapper userRegRequestMapper;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final RoleMapper roleMapper;

    public UserController(RoleService roleService, UserService userService, UserRegRequestMapper userRegRequestMapper,
                          UserRequestMapper userRequestMapper, UserResponseMapper userResponseMapper,
                          RoleMapper roleMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.userRegRequestMapper = userRegRequestMapper;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
        this.roleMapper = roleMapper;
    }

//    @PostMapping("/register")
//    @Operation(summary = "Registration of new user.")
//    public ResponseEntity<String> userRegistration(@RequestBody UserRegRequestDto userRegRequestDto) {
//        User user = userService.getUserByEmail(userRegRequestDto.getEmail());
//        if (user != null) {
//            return new ResponseEntity<>("User with such email is already registered.", HttpStatus.BAD_REQUEST);
//        } else {
//            userService.saveUser(userRegRequestMapper.map(userRegRequestDto));
//            return new ResponseEntity<>("User was registered successfully.", HttpStatus.OK);
//        }
//    }

  //  @PutMapping(value = "register/authenticated/{user_id}")
    @Operation(summary = "Edit created users.")
    // @PreAuthorize(value = "hasAnyRole('USER')")
    @SecurityRequirement(name = "Basic_type")
    public ResponseEntity<UserResponseDto> userEdit(@PathVariable Integer user_id, @RequestBody List<String> roles) {
        User user = userService.getuserById(user_id);
        List<String> newRoles = new ArrayList<>();
        List<String> constRoles = roleService.getAllRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        for (String strTemp1 : roles) {
            for (String strtemp2 : constRoles) {
                if (strtemp2.equalsIgnoreCase(strTemp1)) {
                    newRoles.add(strTemp1.toUpperCase());
                }
            }
        }

        if (user == null || newRoles.isEmpty()) {
            return new ResponseEntity<>(new UserResponseDto(), HttpStatus.BAD_REQUEST);
        } else {
            userService.editUserRole(user, newRoles);
        }
        return new ResponseEntity<>(userResponseMapper.map(user), HttpStatus.OK);
    }


//    @PostMapping("/login")
//    @Operation(summary = "Login user.")
//    public ResponseEntity<String> userLogin() {
//
//        return new ResponseEntity<>("User was logout successfully.", HttpStatus.OK);
//    }
//
//    @PostMapping("/logout")
//    @Operation(summary = "Logout user.")
//    public ResponseEntity<String> userLogout() {
//
//        return new ResponseEntity<>("User was logout successfully.", HttpStatus.OK);
//    }

    @GetMapping("/all_users")
    @Operation(summary = "Get all users.")
//    @PreAuthorize(value = "hasAnyRole('USER')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userResponseMapper.toList(userService.getAllUsers());
        return ResponseEntity.ok(userResponseDtos);
    }

    @GetMapping("/test_2")
    public ResponseEntity<UserResponseDto> testMethod() {
 //      User user = userService.getuserById(1);
        UserResponseDto dto = new UserResponseDto();//userResponseMapper.map(user);
        dto.setFirstName("MIGHTY MIKE");
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer user_id) {
        return ResponseEntity.ok(userResponseMapper.map(userService.getuserById(user_id)));
    }
}
