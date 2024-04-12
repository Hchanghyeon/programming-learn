// ChatRoomCreatePage.js
import React, { useState } from 'react';
import { Container, Input, Button } from './ChatRoomCreatePageStyle';
import { Header } from '../../components/Header/Header';
import axios from 'axios';
import { createChatRoom } from '../../api/chats/createChatRoom';

function ChatRoomCreatePage() {
  const [title, setTitle] = useState('');
  const [maxMemberCount, setMaxMemberCount] = useState(2);

  const accessToken = localStorage.getItem('accessToken');

  if(!accessToken){
      alert("로그인이 되지 않아 채팅방을 생성할 수 없습니다.");
      window.history.go(-1);
      return;
  }

  const handleCreateRoom = async () => {
    try {
      // 여기서는 POST 요청을 예시로 들었습니다. 실제 요청 URL과 데이터 구조는 서버의 API 명세에 따라 다를 수 있습니다.
      const response = await createChatRoom({title, maxMemberCount})
      console.log('Room created:', response.data);
      window.location.href="/chatrooms"
      // 채팅방 생성 후 필요한 로직을 추가할 수 있습니다. 예를 들어, 생성된 채팅방으로 리다이렉트하는 등
    } catch (error) {
      console.error('Error creating room:', error);
    }
  };

  return (
    <>
    <Header/>
    <Container>
      <h2>채팅방 생성</h2>
      <Input
        type="text"
        placeholder="채팅방 제목"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <Input
        type="number"
        placeholder="정원 수"
        value={maxMemberCount}
        onChange={(e) => setMaxMemberCount(e.target.value)}
      />
      <Button onClick={handleCreateRoom}>생성하기</Button>
    </Container>
    </>
  );
}

export default ChatRoomCreatePage;