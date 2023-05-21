package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.Message;
import com.mike.usermessages.service.dto.MessageResponseDto;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MessageResponseMapper {

    MessageResponseDto map(Message message);

    Message map(MessageResponseDto messageResponseDto);

    default Set<MessageResponseDto> toSet(Set<Message> messageSet) {
        return messageSet.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }
}
