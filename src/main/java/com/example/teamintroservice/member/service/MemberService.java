package com.example.teamintroservice.member.service;

import com.example.teamintroservice.common.exception.MemberNotFoundException;
import com.example.teamintroservice.member.dto.MemberRequest;
import com.example.teamintroservice.member.dto.MemberResponse;
import com.example.teamintroservice.member.entity.Member;
import com.example.teamintroservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse saveMember(MemberRequest request) {
        Member member = new Member(request.getName(), request.getAge(), request.getMbti(), null);
        log.info("[API - Log] 멤버 정보 저장");
        return new MemberResponse(memberRepository.save(member));
    }

    public MemberResponse getOneMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("없는 팀원입니다.")
        );
        log.info("[API - Log] 멤버 정보 조회 - memberId: {}", memberId);
        return new MemberResponse(member);
    }
}
