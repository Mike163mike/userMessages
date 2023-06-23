package com.mike.usermessages.mapper;

import com.mike.usermessages.model.Message;
import com.mike.usermessages.mapper.dto.MessageRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface MessageRequestMapper {

    MessageRequestDto map(Message message);

    Message map(MessageRequestDto messageRequestDto);

}
