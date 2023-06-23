package com.mike.usermessages.mapper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {

    private String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
