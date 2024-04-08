import React, { useEffect, useState } from 'react';
import { HeaderContainer, Logo, Navigation, StyledLink, NicknameContent, StyledA } from './HeaderStyle';

export const Header = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [nickname, setNickname] = useState("");

    useEffect(() => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            setIsLoggedIn(true); // accessToken이 있으면 로그인 상태로 설정
            setNickname(localStorage.getItem('nickname'));
        } else {
            setIsLoggedIn(false); // 없으면 로그아웃 상태로 설정
            setNickname("");
        }
    }, []); 

    return (
        <HeaderContainer>
            <Logo>
                <StyledA href="/">chat</StyledA>
            </Logo>
            <Navigation>
                {isLoggedIn ? (
                    <>
                        <NicknameContent>
                            {nickname} 님
                        </NicknameContent>
                    </>

                ) : (
                    <>
                        <StyledLink to="/login">로그인</StyledLink>
                        <StyledLink to="/register">회원가입</StyledLink>
                    </>
                )}
            </Navigation>
        </HeaderContainer>
    );
};