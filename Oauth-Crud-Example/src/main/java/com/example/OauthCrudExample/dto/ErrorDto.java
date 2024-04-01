package com.example.OauthCrudExample.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder // build the object in efficent way
public class ErrorDto {
    private String message;
    private LocalDateTime time;
}
