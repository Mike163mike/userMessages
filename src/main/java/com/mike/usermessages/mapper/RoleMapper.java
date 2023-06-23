package com.mike.usermessages.mapper;

import com.mike.usermessages.model.Role;
import com.mike.usermessages.mapper.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleDto map(Role role);
    Role map(RoleDto roleDto);
}
