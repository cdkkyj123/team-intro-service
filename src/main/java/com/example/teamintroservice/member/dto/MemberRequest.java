package com.example.teamintroservice.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @Positive(message = "나이는 0보다 커야합니다.")
    private int age;
    private String mbti;
}
