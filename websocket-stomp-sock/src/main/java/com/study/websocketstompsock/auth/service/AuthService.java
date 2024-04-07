package com.study.websocketstompsock.auth.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.websocketstompsock.auth.dto.AccessTokenResponse;
import com.study.websocketstompsock.auth.dto.MemberLoginRequest;
import com.study.websocketstompsock.member.domain.Member;
import com.study.websocketstompsock.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AccessTokenResponse memberLogin(final MemberLoginRequest memberLoginRequest) {
        final Member member = getMember(memberLoginRequest);
        validateMemberPassword(memberLoginRequest.password(), member.getPassword());

        final String accessToken = jwtProvider.generateAccessToken(member.getEmail());

        return AccessTokenResponse.from(accessToken);
    }

    private void validateMemberPassword(final String requestPassword, final String encryptedPassword) {
        if (!passwordEncoder.matches(requestPassword, encryptedPassword)) {
            throw new BadCredentialsException("사용자 정보가 잘못되었습니다.");
        }
    }

    private Member getMember(final MemberLoginRequest memberLoginRequest) {
        return memberRepository.findByEmail(memberLoginRequest.email())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
