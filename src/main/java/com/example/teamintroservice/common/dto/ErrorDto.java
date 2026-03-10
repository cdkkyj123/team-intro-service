package com.example.teamintroservice.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDto <T> implements ApiResponseDto<T>{
    private final String message;
    private final T data;
}