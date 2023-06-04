package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.User;
import com.mike.usermessages.service.dto.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseDto map(User user);

    User map(UserResponseDto userResponseDto);

    default List<UserResponseDto> toList(List<User> users) {
        return users.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
