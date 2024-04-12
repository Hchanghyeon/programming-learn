// ChatRoomList.js
import React, { useState, useEffect } from 'react';
import { ListContainer, RoomContainer, Title, Details, Button, ButtonContainer, ContentContainer, TitleContainer } from './ChatRoomListPageStyle';
import { getChatRooms } from '../../api/chats/getChatRooms';
import { formatDate } from '../../utils/DateChange';
import { Header } from '../../components/Header/Header';
import { enterChatRoom } from '../../api/chats/enterChatRoom';
import FloatingMenu from '../../components/FloatingMenu/FloatingMenu';

export function ChatRoomList() {
    const [chatRooms, setChatRooms] = useState([]);

    useEffect(() => {
        // API 호출을 위한 함수
        const fetchChatRooms = async () => {
            try {
                const response = await getChatRooms();
                setChatRooms(response); // 받아온 데이터로 상태 업데이트
            } catch (error) {
                console.error(error);
            }
        };

        fetchChatRooms();
    }, [enterChatRoom]);

    const handleButton = async (chatRoomId) => {
        const accessToken = localStorage.getItem('accessToken');
        if(!accessToken){
            alert("로그인이 되지 않아 채팅방을 생성할 수 없습니다.");
            return;
        }      

        const data = await enterChatRoom(chatRoomId);
        console.log(data);
    }

    return (
        <>
            <Header />
            <ListContainer>
                {chatRooms.map(room => (
                    <RoomContainer key={room.chatRoomId}>
                        <TitleContainer>
                            <Title>{room.title}</Title>
                            <Details>참여 인원 {room.currentMemberCount}/{room.maxMemberCount}</Details>
                        </TitleContainer>
                        <ContentContainer>
                        <Details>방장 {room.hostNickname}</Details><Details>{formatDate(new Date(room.createdAt))}</Details>
                        </ContentContainer>
                        <ButtonContainer>
                            <Button onClick={() => handleButton(room.chatRoomId)}>참여하기</Button>
                        </ButtonContainer>
                    </RoomContainer>
                ))}
            </ListContainer>
            <FloatingMenu/>
        </>
    );
}
