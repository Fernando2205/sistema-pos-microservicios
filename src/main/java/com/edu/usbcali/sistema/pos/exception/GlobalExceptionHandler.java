package com.edu.usbcali.sistema.pos.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<ErrorDetails> handleValidationException(
                        ValidationException ex, WebRequest request) {
                ErrorDetails errorDetails = ErrorDetails.builder()
                                .errorCode("VALIDATION_ERROR")
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .path(request.getDescription(false).replace("uri=", ""))
                                .build();

                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
                        ResourceNotFoundException ex, WebRequest request) {
                ErrorDetails errorDetails = ErrorDetails.builder()
                                .errorCode("RESOURCE_NOT_FOUND")
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .path(request.getDescription(false).replace("uri=", ""))
                                .build();

                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetails> handleGenericException(
                        Exception ex, WebRequest request) {
                ErrorDetails errorDetails = ErrorDetails.builder()
                                .errorCode("INTERNAL_SERVER_ERROR")
                                .message(ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .path(request.getDescription(false).replace("uri=", ""))
                                .build();

                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
