package com.example.teamintroservice.member.controller;

import com.example.teamintroservice.member.dto.MemberRequest;
import com.example.teamintroservice.member.dto.MemberResponse;
import com.example.teamintroservice.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<MemberResponse> saveMember(
            @RequestBody MemberRequest request
    ) {
        log.info("[API - Log] 멤버 정보 저장 시도");
        return ResponseEntity.ok(memberService.saveMember(request));
    }

    @GetMapping("/{memberId}")
    ResponseEntity<MemberResponse> getOneMember(
            @PathVariable Long memberId
    ) {
        log.info("[API - Log] 멤버 정보 조회 시도");
        return ResponseEntity.ok(memberService.getOneMember(memberId));
    }
}
