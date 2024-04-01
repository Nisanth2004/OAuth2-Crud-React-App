package com.example.OauthCrudExample.controller;

import com.example.OauthCrudExample.dto.ErrorDto;
import com.example.OauthCrudExample.exception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor
{
    @ExceptionHandler(value={NotFoundException.class})
  public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException exception)
  {
       return ResponseEntity.badRequest().body(ErrorDto.builder()
               .message(exception.getMessage())
               .time(LocalDateTime.now())
               .build());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
  {
      return ResponseEntity.badRequest().body(ErrorDto.builder()
              .message(exception.getFieldErrors().stream()
                      .map(DefaultMessageSourceResolvable::getDefaultMessage)
                      .collect(Collectors.joining(",")))
              .time(LocalDateTime.now())
              .build());
  }
}
