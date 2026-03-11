package com.example.teamintroservice.file.service;

import com.example.teamintroservice.common.exception.FileUploadFailException;
import com.example.teamintroservice.common.exception.MemberNotFoundException;
import com.example.teamintroservice.member.repository.MemberRepository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Template s3Template;
    private final MemberRepository memberRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloudfront_domain}")
    private String cloudFrontDomain;

    public String upload(Long memberId, MultipartFile file) {
        findMember(memberId);
        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());
            return key;
        } catch (IOException e) {
            throw new FileUploadFailException("파일 업로드 실패");
        }
    }

    public String getDownloadUrl(Long memberId, String key) {
        findMember(memberId);
        return cloudFrontDomain + "/" + key;
    }

    public void findMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberNotFoundException("없는 팀원입니다.");
        }
    }
}