package com.example.teamintroservice.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class SuccessDto<T> implements ApiResponseDto<T> {
    private final HttpStatus status;
    private final T data;
}
