package com.mike.usermessages.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessageResponseDto {

    private Integer id;
    private String message;
    private Instant createTime;
    private Instant editTime;
    private Integer user_id;
}
