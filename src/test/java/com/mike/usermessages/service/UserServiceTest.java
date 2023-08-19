package com.mike.usermessages.service;

import com.mike.usermessages.AbstractTest;
import com.mike.usermessages.model.Role;
import com.mike.usermessages.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    void editUserRoles() {
        User user = new User();
        Role roleU = new Role();
        roleU.setName("ROLE_USER");
        user.setRoles(List.of(roleU));
        user.setUsername("User");
        userRepository.save(user);
        user = userService.editUserRoles("User", List.of("ROLE_ADMIN"));
        String newRole = user.getRoles().stream()
            .map(Role::getName)
            .findFirst()
            .orElseThrow();
        Assertions.assertArrayEquals("ROLE_ADMIN".toCharArray(), newRole.toCharArray());
    }

    @Test
    void mapStringToRole() {
        List<String> stringList = Arrays.asList("ADMIN", "USER");
        String[] strings = stringList.toArray(new String[0]);
        Role roleA = new Role();
        Role roleU = new Role();
        roleA.setName("USER");
        roleU.setName("USER");
        List<Role> results = userService.mapStringToRole(stringList);
        Role[] rolesArray = results.toArray(new Role[0]);
        String[] rolesAsString = Arrays.stream(rolesArray)
            .map(Role::getName).toArray(String[]::new);
        Assertions.assertArrayEquals(strings, rolesAsString);
    }
}