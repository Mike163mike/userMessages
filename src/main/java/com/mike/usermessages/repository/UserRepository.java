package com.mike.usermessages.repository;

import com.mike.usermessages.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

   User findUserByUsername(String username);
   Integer deleteUserByUsername(String username); //Why the method can return Integer type only?*********
}
