package com.telran.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ErrorDto {
    LocalDateTime timestamp;
    String message;
    int status;
}