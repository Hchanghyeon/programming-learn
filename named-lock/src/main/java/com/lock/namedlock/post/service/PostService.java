package com.lock.namedlock.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lock.namedlock.member.domain.Member;
import com.lock.namedlock.member.domain.MemberRepository;
import com.lock.namedlock.post.domain.Post;
import com.lock.namedlock.post.domain.PostRepository;
import com.lock.namedlock.post.dto.PostCreateRequest;
import com.lock.namedlock.post.dto.PostResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final Long LIMIT_POST_CREATE = 3L;

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::of)
                .toList();
    }

    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
    public PostResponse createPost(final PostCreateRequest request) {
        final Long memberId = request.getMemberId();
        final Long createdPostCount = postRepository.countByMemberId(memberId);

        if (createdPostCount >= LIMIT_POST_CREATE) {
            throw new RuntimeException("LIMIT POST ID: " + memberId);
        }

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("MEMBER NOT FOUNDED"));

        final Post post = request.toEntity(member);

        return PostResponse.of(postRepository.save(post));
    }
}
