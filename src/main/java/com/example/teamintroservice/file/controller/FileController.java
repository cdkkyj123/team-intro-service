package com.example.teamintroservice.file.controller;

import com.example.teamintroservice.common.dto.ApiResponseDto;
import com.example.teamintroservice.file.dto.FileDownloadUrlResponse;
import com.example.teamintroservice.file.dto.FileUploadResponse;
import com.example.teamintroservice.file.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/{memberId}/profile-image")
@Slf4j
public class FileController {

    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<ApiResponseDto<FileUploadResponse>> uploadImage(
            @PathVariable Long memberId,
            @RequestParam("file") MultipartFile file
    ) {
        String key = s3Service.upload(memberId, file);
        log.info("[API - Log] 멤버 이미지 저장 시도 - memberId: {}", memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(HttpStatus.CREATED, new FileUploadResponse(key)));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<FileDownloadUrlResponse>> getImage(
            @PathVariable Long memberId,
            @RequestParam String key
    ) {
        String url = s3Service.getDownloadUrl(memberId, key);
        log.info("[API - Log] 멤버 이미지url 조회 시도 - memberId: {}", memberId);
        return ResponseEntity
                .ok(ApiResponseDto.success(HttpStatus.OK, new FileDownloadUrlResponse(url)));
    }
}