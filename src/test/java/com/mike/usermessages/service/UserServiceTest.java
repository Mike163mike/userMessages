package com.mike.usermessages.service;

import com.mike.usermessages.model.Role;
import com.mike.usermessages.model.User;
import com.mike.usermessages.repository.RoleRepository;
import com.mike.usermessages.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserService userService;

    public User getUser() {
        User user = new User();
        user.setUsername("User");
        Role roleU = new Role();
        roleU.setName("USER");
        user.setRoles(List.of(roleU));
        return user;
    }

    private List<Role> getRole() {
        Role roleU = new Role();
        Role roleA = new Role();
        roleU.setName("USER");
        roleU.setName("ADMIN");
        return List.of(roleU, roleA);
    }

    @Test
    void editUserRoles() {
        User user = new User();
        Role roleU = new Role();
        roleU.setName("USER");
        user.setRoles(List.of(roleU));
        user.setUsername("User");
        Mockito.when(userRepository.findUserByUsername("User")).thenReturn(getUser());
        Mockito.when(roleRepository.findAll()).thenReturn(getRole());
        user = userService.editUserRoles("User", List.of("ADMIN"));
        String newRole = user.getRoles().stream()
                .map(Role::getName)
                .findFirst()
                .orElseThrow();
        Assertions.assertArrayEquals("ADMIN".toCharArray(), newRole.toCharArray());
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