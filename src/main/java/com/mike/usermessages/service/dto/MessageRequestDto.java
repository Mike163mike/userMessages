package com.mike.usermessages.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDto {

    private String message;
    private Integer user_id;
}
