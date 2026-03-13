package com.example.teamintroservice.file.service;

import com.example.teamintroservice.common.exception.FileUploadFailException;
import com.example.teamintroservice.common.exception.MemberNotFoundException;
import com.example.teamintroservice.member.entity.Member;
import com.example.teamintroservice.member.repository.MemberRepository;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private final S3Template s3Template;
    private final MemberRepository memberRepository;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.cloudfront.domain}")
    private String cloudFrontDomain;

    @Transactional
    public String upload(Long memberId, MultipartFile file) {
        findMember(memberId);
        try {
            String key = "uploads/" + UUID.randomUUID() + "_"
                    + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")
            );
            s3Template.upload(bucket, key, file.getInputStream());
            log.info("[API - Log] 멤버 이미지 저장 - memberId: {}", memberId);
            return key;
        } catch (IOException e) {
            throw new FileUploadFailException("파일 업로드 실패");
        }
    }

    @Transactional
    public String getDownloadUrl(Long memberId, String key) {
        Member member = findMember(memberId);
        String url = cloudFrontDomain + "/" + key;
        member.update(url);
        log.info("[API - Log] 멤버 이미지url 조회 - memberId: {}", memberId);
        return url;
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("없는 팀원입니다.")
        );
    }
}