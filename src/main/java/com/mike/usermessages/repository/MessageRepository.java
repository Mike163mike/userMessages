package com.mike.usermessages.repository;

import com.mike.usermessages.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
