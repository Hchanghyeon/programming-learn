package com.study.websocketstompsock.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.websocketstompsock.member.domain.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Role role;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));

        return grantedAuthorities;
    }

    @Builder
    public MemberDetails(final String username, final String password, final Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
