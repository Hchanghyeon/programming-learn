package com.study.websocketstompsock.member.repository;

import java.util.Optional;

import com.study.websocketstompsock.member.domain.Member;

public interface MemberRepository {

    Member save(final Member member);

    boolean existsByEmail(final String email);

    Optional<Member> findByEmail(String email);
}
