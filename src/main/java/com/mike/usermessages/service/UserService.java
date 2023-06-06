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

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleRepository repository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(Instant.now());
        user.setEditTime(Instant.now());
        user.setRoles(mapStringToRole(List.of("ROLE_USER")));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void editUserRole(User user, List<String> roles) {
        List<Role> roleSet = new ArrayList<>();
        for (String str : roles) {
            Role newRole = new Role();
            newRole.setName(str);
            roleSet.add(newRole);
        }
        user.setRoles(roleSet);
        user.setEditTime(Instant.now());
        userRepository.save(user);
    }

    public void editUserRole(User user, String role) {
        List<Role> roles = user.getRoles();
        Role newRole = new Role();
        newRole.setName(role);
        roles.add(newRole);
        user.setRoles(roles);
        user.setEditTime(Instant.now());
        userRepository.save(user);
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
}
