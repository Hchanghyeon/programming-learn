package com.study.websocketstompsock.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.websocketstompsock.auth.exception.AccessDeniedHandlerException;
import com.study.websocketstompsock.auth.exception.AuthenticationEntryPointException;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPointException authenticationEntryPointException;
    private final AccessDeniedHandlerException accessDeniedHandlerException;

    @Bean
    public SecurityFilterChain filterChain(
            final HttpSecurity httpSecurity,
            final JwtProvider jwtProvider,
            final MemberDetailsService memberDetailsService
    ) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/members", "/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new JwtFilter(memberDetailsService, jwtProvider),
                        UsernamePasswordAuthenticationFilter.class
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(accessDeniedHandlerException);
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPointException);
                });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
