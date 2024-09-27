package com.reservamentor.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object>
  handleResourceNotFoundException(ResourceNotFoundException ex,
                                  WebRequest request) {
    CustomErrorResponse err = new CustomErrorResponse(
        LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object>
  handleBadRequestException(BadRequestException ex, WebRequest request) {
    CustomErrorResponse err = new CustomErrorResponse(
        LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }
}