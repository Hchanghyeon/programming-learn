package com.lock.namedlock.post.dto;

import com.lock.namedlock.member.domain.Member;
import com.lock.namedlock.post.domain.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateRequest {

    private String title;
    private String content;
    private Long memberId;

    public Post toEntity(Member member) {
        return new Post(
                title,
                content,
                member
        );
    }
}
