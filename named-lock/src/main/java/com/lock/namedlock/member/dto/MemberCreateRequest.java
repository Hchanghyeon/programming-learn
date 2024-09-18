package com.lock.namedlock.member.dto;

import com.lock.namedlock.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreateRequest {

    private String email;
    private String password;
    private String name;
    private Long age;

    public Member toEntity() {
        return new Member(
                email,
                password,
                name,
                age
        );
    }
}
