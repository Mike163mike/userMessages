package com.mike.usermessages.service;

import com.mike.usermessages.exception.AppException;
import com.mike.usermessages.model.Role;
import com.mike.usermessages.model.User;
import com.mike.usermessages.repository.RoleRepository;
import com.mike.usermessages.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(Instant.now());
        user.setEditTime(Instant.now());
        user.setRoles(mapStringToRole(List.of("ROLE_USER")));
        user.setEnable(true);
        userRepository.save(user);
    }

    public User editUserRoles(String username, List<String> roles) {
        User user = userRepository.findUserByUsername(username);
        if (checkList(roles)) {
            user.setRoles(mapStringToRole(roles));
        } else {
            throw new AppException("Incorrect name of role.");
        }
        user.setEditTime(Instant.now());
        userRepository.save(user);    //These two steps do-
        return user;                  // only for normal running test cases.
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

    @Transactional
    public Integer deleteUser(String username) {
        return userRepository.deleteUserByUsername(username);
    }

    public List<Role> mapStringToRole(List<String> strings) {
        return getRoles(strings);
    }

    private boolean checkString(String string, List<String> roles) {
        boolean flag = false;
        for (String tempRole : roles) {
            if (string.equalsIgnoreCase(tempRole)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private boolean checkList(List<String> roles) {
        List<String> stringList = new ArrayList<>();
        List<String> constRoles = roleRepository.findAll().stream()
                .map(Role::getName)
                .distinct()
                .collect(Collectors.toList());
        for (String tempStr : roles) {
            if (checkString(tempStr, constRoles)) {
                stringList.add(tempStr);
            }
        }
        return roles.equals(stringList);
    }

    private List<Role> getRoles(List<String> strings) {
        List<Role> newRoles = new ArrayList<>();
        for (String tempStr : strings) {
            Role newRole = new Role();
            newRole.setName(tempStr.toUpperCase());
            newRoles.add(newRole);
        }
        return newRoles;
    }
}

