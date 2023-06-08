package com.mike.usermessages.controller;

import com.mike.usermessages.service.MessageService;
import com.mike.usermessages.service.dto.MessageRequestDto;
import com.mike.usermessages.service.dto.MessageResponseDto;
import com.mike.usermessages.service.mapper.MessageRequestMapper;
import com.mike.usermessages.service.mapper.MessageResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/message")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Messages", description = "The Message API. Contains operations with " +
        "messages.")
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

    @Operation(summary = "Create new message")
    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        MessageResponseDto newMessageResponseDto = messageResponseMapper.map(messageService
                .saveMessage(messageRequestMapper
                        .map(messageRequestDto)));
        return ResponseEntity.ok(newMessageResponseDto);
    }

    @Operation(summary = "Get all messages", description = "get all messages from all " +
            "users.")
    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getAllMessages() {
        List<MessageResponseDto> messageResponseDtos = messageResponseMapper
                .toList(messageService.getAllMessages());
        return ResponseEntity.ok(messageResponseDtos);
    }

    @GetMapping("/{message_id}")
    @Operation(summary = "Get message by id")
    public ResponseEntity<MessageResponseDto> getMessageById(@PathVariable Integer message_id) {
        try {
            messageService.findById(message_id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found message with id = " + message_id + ".");
        }
        MessageResponseDto messageResponseDto = messageResponseMapper.map(messageService.findById(message_id));
        return ResponseEntity.ok(messageResponseDto);
    }

    @PutMapping("/{message_id}")
    @Operation(summary = "Edit message")
    public ResponseEntity<MessageResponseDto> editMessageById(@PathVariable Integer message_id,
                                                              @RequestBody String message) {
        try {
            messageService.findById(message_id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found message with id = " + message_id + ".");
        }
        MessageResponseDto messageResponseDto = messageResponseMapper.map(
                messageService.editMessageById(message_id, message));
        return ResponseEntity.ok(messageResponseDto);
    }

    @DeleteMapping("/{message_id}")
    @Operation(summary = "Delete message by id")
    public ResponseEntity<String> deleteMessageById(@PathVariable Integer message_id) {
        try {
            messageService.findById(message_id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Not found message with id = " + message_id + ".", HttpStatus.NOT_FOUND);
        }
        messageService.deleteMessageById(message_id);
        return ResponseEntity.ok("The message with id = " + message_id + " was deleted successfully.");
    }
}
