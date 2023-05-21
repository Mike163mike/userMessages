package com.mike.usermessages.controller;

import com.mike.usermessages.service.MessageService;
import com.mike.usermessages.service.dto.MessageRequestDto;
import com.mike.usermessages.service.dto.MessageResponseDto;
import com.mike.usermessages.service.mapper.MessageRequestMapper;
import com.mike.usermessages.service.mapper.MessageResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class MessageController {

    private final MessageService messageService;
    private final MessageResponseMapper messageResponseMapper;
    private final MessageRequestMapper messageRequestMapper;

    public MessageController(MessageService messageService, MessageResponseMapper messageResponseMapper,
                             MessageRequestMapper messageRequestMapper) {
        this.messageService = messageService;
        this.messageResponseMapper = messageResponseMapper;
        this.messageRequestMapper = messageRequestMapper;
    }

    @PostMapping
    @Operation(summary = "Create new message")
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        MessageResponseDto newMessageResponseDto = messageResponseMapper.map(messageService.saveMessage(messageRequestMapper
                .map(messageRequestDto)));
        return ResponseEntity.ok(newMessageResponseDto);
    }

    @GetMapping
    @Operation(summary = "Get all messages")
    public ResponseEntity<Set<MessageResponseDto>> getAllMessages() {
        Set<MessageResponseDto> messageResponseDtos = messageResponseMapper.toSet(messageService.getAllMessages());
        return ResponseEntity.ok(messageResponseDtos);
    }

    @GetMapping("/{task_id}")
    @Operation(summary = "Get message by id")
    public ResponseEntity<MessageResponseDto> getMessageById(@PathVariable Integer task_id) {
        MessageResponseDto messageResponseDto = messageResponseMapper.map(messageService.findById(task_id));
        return ResponseEntity.ok(messageResponseDto);
    }

    @PutMapping("/{task_id}")
    @Operation(summary = "Edit message")
    public ResponseEntity<MessageResponseDto> editMessageById(@PathVariable Integer task_id,
                                                              @RequestBody String message) {
        try {
            messageService.findById(task_id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found message with id = " + task_id + ".");
        }
        MessageResponseDto messageResponseDto = messageResponseMapper.map(
                messageService.editMessageById(task_id, message));
        return ResponseEntity.ok(messageResponseDto);
    }

    @DeleteMapping("/{task_id}")
    @Operation(summary = "Delete message by id")
    public ResponseEntity<String> deleteMessageById(@PathVariable Integer task_id) {
        try {
            messageService.findById(task_id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Not found message with id = " + task_id + ".", HttpStatus.NOT_FOUND);
        }
        messageService.deleteMessageById(task_id);
        return ResponseEntity.ok("The message with id = " + task_id + " was deleted successfully.");
    }
}
