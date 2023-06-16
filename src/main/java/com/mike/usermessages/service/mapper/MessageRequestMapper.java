package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.Message;
import com.mike.usermessages.service.dto.MessageRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface MessageRequestMapper {

    MessageRequestDto map(Message message);

    Message map(MessageRequestDto messageRequestDto);

}
