package com.study.websocketstompsock.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.websocketstompsock.member.domain.Member;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

}
