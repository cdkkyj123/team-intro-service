package com.example.teamintroservice.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String mbti;
    private String profileImageUrl;

    public Member(String name, int age, String mbti, String profileImageUrl) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
        this.profileImageUrl = profileImageUrl;
    }

    public void update(String url) {
        this.profileImageUrl = url;
    }
}
