package com.mike.usermessages.repository;

import com.mike.usermessages.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usr, Integer> {
}
