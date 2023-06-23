package com.mike.usermessages.mapper;

import com.mike.usermessages.model.User;
import com.mike.usermessages.mapper.dto.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserResponseMapper {

    UserResponseDto map(User user);

    User map(UserResponseDto userResponseDto);

    default List<UserResponseDto> toList(List<User> users) {
        return users.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
