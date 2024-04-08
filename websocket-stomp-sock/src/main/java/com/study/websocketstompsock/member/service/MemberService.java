package com.study.websocketstompsock.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.websocketstompsock.member.domain.Member;
import com.study.websocketstompsock.member.dto.MemberCreateRequest;
import com.study.websocketstompsock.member.dto.MemberIdResponse;
import com.study.websocketstompsock.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberIdResponse registerMember(final MemberCreateRequest memberCreateRequest) {
        checkMemberExists(memberCreateRequest);
        final String encryptedPassword = passwordEncoder.encode(memberCreateRequest.password());
        final Member member = MemberCreateRequest.toMemberEntity(memberCreateRequest, encryptedPassword);

        final Member savedMember = memberRepository.save(member);

        return MemberIdResponse.from(savedMember.getId());
    }

    private void checkMemberExists(final MemberCreateRequest memberCreateRequest) {
        final boolean isMemberExists = memberRepository.existsByNicknameOrEmail(
                memberCreateRequest.nickname(),
                memberCreateRequest.email()
        );

        if (isMemberExists) {
            throw new EntityNotFoundException("이미 사용자가 존재합니다.");
        }
    }
}
