package com.lock.namedlock.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lock.namedlock.member.domain.Member;
import com.lock.namedlock.member.domain.MemberRepository;
import com.lock.namedlock.member.dto.MemberCreateRequest;
import com.lock.namedlock.member.dto.MemberResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponse> getMembers() {
        final List<Member> memberEntities = memberRepository.findAll();

        return memberEntities.stream()
                .map(MemberResponse::of)
                .toList();
    }

    @Transactional
    public MemberResponse createMember(final MemberCreateRequest request) {
        return MemberResponse.of(memberRepository.save(request.toEntity()));
    }
}
