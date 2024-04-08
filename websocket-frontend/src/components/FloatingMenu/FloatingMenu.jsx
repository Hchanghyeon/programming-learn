import React, { useState } from 'react';
import { Button, FloatingButton, MenuContainer, MenuItem } from './FloatingMenuStyle';

const FloatingMenu = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    const handleLogout = () => {
        localStorage.removeItem('accessToken'); // 로컬 스토리지에서 accessToken 제거
        localStorage.removeItem('nickname');
        location.href = "/";
    };

    return (
        <>
            <FloatingButton onClick={toggleMenu}>
                +
            </FloatingButton>
            {isOpen && (
                <MenuContainer>
                    <MenuItem><Button onClick={handleLogout}>로그아웃</Button></MenuItem>
                    <MenuItem><Button>채팅방 생성</Button></MenuItem>
                    <MenuItem><Button>내 채팅방 목록</Button></MenuItem>
                    <MenuItem><Button>전체 채팅방 목록</Button></MenuItem>
                </MenuContainer>
            )}
        </>
    );
};

export default FloatingMenu;