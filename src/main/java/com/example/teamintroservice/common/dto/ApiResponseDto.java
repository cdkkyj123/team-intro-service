package com.example.teamintroservice.common.dto;

import org.springframework.http.HttpStatus;

public interface ApiResponseDto<T> {

    static <T> ApiResponseDto<T> success(HttpStatus status, T data) {
        return new SuccessDto<>(status, data);
    }

    static <T> ApiResponseDto<T> error(String message) {

        return new ErrorDto<>(message, null);
    }
}
