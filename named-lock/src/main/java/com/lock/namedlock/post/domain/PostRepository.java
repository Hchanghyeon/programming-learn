package com.lock.namedlock.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Long countByMemberId(Long memberId);
}
