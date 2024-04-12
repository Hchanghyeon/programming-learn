// ChatRoomPage.js
import React, { useState, useEffect } from 'react';
import styled, { css } from 'styled-components';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { getChatRoomMessages } from '../../api/chats/getChatRoomMessages';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
`;

const Messages = styled.div`
  width: 90%;
  height: 400px;
  border: 1px solid #ccc;
  overflow-y: auto;
  margin-bottom: 20px;
`;

const Message = styled.div`
  padding: 10px;
  margin: 5px;
  border-radius: 10px;
  background-color: #f0f0f0;
  max-width: 60%;
  ${(props) =>
    props.sender === 'me'
      ? css`
          align-self: flex-end;
          background-color: #dcf8c6;
        `
      : css`
          align-self: flex-start;
        `}
`;

const Input = styled.input`
  width: 80%;
  padding: 10px;
  margin-right: 10px;
`;

const Button = styled.button`
  padding: 10px 20px;
`;

// API 함수 가정. 실제 구현에서는 서버로부터 데이터를 받아오는 로직으로 대체해야 합니다.
const fetchMessages = async () => {
  // 실제 구현에서는 API 호출을 통해 메시지 리스트를 불러옵니다.
  return await getChatRoomMessages(1);
};

function ChatRoomPage() {
  const [socket, setSocket] = useState(null);
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [currentMessage, setCurrentMessage] = useState('');

  useEffect(() => {
        // SockJS와 Stomp 설정
        const socket = new SockJS('http://localhost:8080/chat');
        const stompClient = Stomp.over(socket);
        setSocket(socket);
        setStompClient(stompClient);
  },[])

  useEffect(() => {
    if(!stompClient || !socket){
        return;
    }

    // 기존 메시지를 불러오는 부분
    const initMessages = async () => {
      const initialMessages = await fetchMessages();
      setMessages(initialMessages);
    };

    initMessages();
    const auth = "Bearer " + localStorage.getItem("accessToken");

    stompClient.connect({
      'Authorization':auth
    }, function(frame) {
      console.log('Connected: ' + frame);

      stompClient.subscribe('/subscribe/rooms/1', function(messageOutput) {
        const message = JSON.parse(messageOutput.body);
        setMessages(prevMessages => [...prevMessages, message.body]);
      });
    });
  }, [socket, stompClient]);

  const sendMessage = () => {
    if (stompClient) {
      const message = { message: currentMessage, senderId: 1 };
      stompClient.send("/publish/rooms/1", {}, JSON.stringify(message));
      setCurrentMessage('');
    }
  };

  return (
    <Container>
      <h2>채팅방</h2>
      <Messages>
        {messages.map((message, index) => (
          <Message key={index} sender={message.sender}>
            {message.message}
          </Message>
        ))}
      </Messages>
      <div>
        <Input
          type="text"
          placeholder="메시지 입력..."
          value={currentMessage}
          onChange={(e) => setCurrentMessage(e.target.value)}
        />
        <Button onClick={sendMessage}>보내기</Button>
      </div>
    </Container>
  );
}

export default ChatRoomPage;