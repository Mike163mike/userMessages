package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.User;
import com.mike.usermessages.service.dto.JwtRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtRequestMapper {

    JwtRequestDto map(User user);

    User map(JwtRequestDto jwtRequestDto);
}
