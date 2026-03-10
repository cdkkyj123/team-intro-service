package com.example.teamintroservice.common.exception;

import com.example.teamintroservice.common.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleServiceException(ServiceException ex) {
        log.error("[API - LOG] error : {}", ex.getMessage(), ex);
        return ResponseEntity.status(ex.getStatus()).body(ApiResponseDto.error(ex.getMessage()));
    }
}
