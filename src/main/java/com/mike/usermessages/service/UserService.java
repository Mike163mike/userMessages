package com.mike.usermessages.service;

import com.mike.usermessages.model.Role;
import com.mike.usermessages.model.User;
import com.mike.usermessages.repository.RoleRepository;
import com.mike.usermessages.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(Instant.now());
        user.setEditTime(Instant.now());
        user.setRoles(mapStringToRole(List.of("ROLE_USER")));
        user.setEnable(true);
        userRepository.save(user);
    }

    public User editUserRoles(Integer id, List<String> roles) {
        User user = userRepository.findById(id)
                .orElseThrow();
        if (listCheck(roles)) {
            user.setRoles(mapStringToRoles(roles));
        } else {
            throw new RuntimeException("Incorrect name of role.");
        }
        user.setEditTime(Instant.now());
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<Role> mapStringToRole(List<String> strings) {
        List<Role> roles = new ArrayList<>();
        for (String tempStr : strings) {
            Role role = new Role();
            role.setName(tempStr);
            roles.add(role);
        }
        return roles;
    }

    private boolean stringCheck(String string, List<String> roles) {
        for (String tempRole : roles) {
            if (string.equals(tempRole)) {
                return true;
            }
        }
        return false;
    }

    private boolean listCheck(List<String> roles) {
        List<String> stringList = new ArrayList<>();
        List<String> constRoles = roleRepository.findAll().stream()
                .map(Role::getName)
                .distinct()
                .collect(Collectors.toList());
        for (String tempStr : roles) {
            if (stringCheck(tempStr, constRoles)) {
                stringList.add(tempStr);
            } else {
                return false;
            }
        }
        return roles.size() == stringList.size();
    }

    private List<Role> mapStringToRoles(List<String> strings) {
        List<Role> newRoles = new ArrayList<>();
        for (String tempStr : strings) {
            Role newRole = new Role();
            newRole.setName(tempStr);
            newRoles.add(newRole);
        }
        return newRoles;
    }
}

