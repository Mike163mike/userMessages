package com.mike.usermessages.service;

import com.mike.usermessages.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UserServiceTest {

    private UserService userService;

    @Test
    void editUserRoles() {

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

        Assertions.assertArrayEquals(strings, rolesArray);
    }
}