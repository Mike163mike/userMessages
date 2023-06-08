//package com.mike.usermessages.service;
//
//import com.mike.usermessages.model.Role;
//import com.mike.usermessages.repository.RoleRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Service
//public class RoleService {
//
//    private final RoleRepository roleRepository;
//
//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public Role saveRole(Role role) {
//        return roleRepository.save(role);
//    }
//
//    public Set<Role> getAllRoles() {
//        return new HashSet<>(roleRepository.findAll());
//    }
//
//    public boolean deleteRoleByName(String name) {
//        roleRepository.deleteByName(name);
//        return true;
//    }
//
//
//}
