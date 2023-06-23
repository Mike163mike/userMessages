package com.mike.usermessages.mapper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegRequestDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
