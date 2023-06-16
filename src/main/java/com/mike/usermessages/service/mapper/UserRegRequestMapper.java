package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.User;
import com.mike.usermessages.service.dto.UserRegRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegRequestMapper {

    UserRegRequestDto map(User user);

    User map(UserRegRequestDto userRegRequestDto);
}
