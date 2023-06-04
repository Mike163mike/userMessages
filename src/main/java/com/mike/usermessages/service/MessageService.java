package com.mike.usermessages.service;

import com.mike.usermessages.model.Message;
import com.mike.usermessages.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        message.setCreateTime(Instant.now());
        message.setEditTime(Instant.now());
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message findById(Integer id) {
        return messageRepository.findById(id)
                .orElseThrow();
    }

    public void deleteMessageById(Integer id) {
        messageRepository.deleteById(id);
    }

    public Message editMessageById(Integer id, String newMessage) {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setEditTime(Instant.now());
        message.setMessage(newMessage);
        messageRepository.save(message);
        return message;
    }
}
