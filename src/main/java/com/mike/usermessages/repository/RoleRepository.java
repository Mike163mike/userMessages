package com.mike.usermessages.repository;

import com.mike.usermessages.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    void deleteByName(String name);
}
