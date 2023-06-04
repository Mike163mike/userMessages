package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.User;
import com.mike.usermessages.service.dto.UserRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    UserRequestDto map(User user);

    User map(UserRequestDto userRequestDto);
}
