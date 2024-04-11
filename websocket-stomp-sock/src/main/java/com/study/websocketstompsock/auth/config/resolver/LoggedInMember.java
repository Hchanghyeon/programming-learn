package com.study.websocketstompsock.auth.config.resolver;

import com.study.websocketstompsock.member.domain.Role;

public record LoggedInMember(Long memberId, String nickname, Role role) {
    
}
