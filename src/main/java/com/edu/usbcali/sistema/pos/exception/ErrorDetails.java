package com.edu.usbcali.sistema.pos.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
