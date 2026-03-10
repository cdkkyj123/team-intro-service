package com.example.teamintroservice.common.exception;

import org.springframework.http.HttpStatus;

public class FileUploadFailException extends ServiceException {
    public FileUploadFailException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
