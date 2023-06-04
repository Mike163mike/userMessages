package com.mike.usermessages.service.mapper;

import com.mike.usermessages.model.Role;
import com.mike.usermessages.service.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto map(Role role);
    Role map(RoleDto roleDto);
}
