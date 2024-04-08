import React, { useState, useEffect } from 'react';
import { LoginContainer, InputGroup, Input, Label, LoginForm, Button } from './LoginPageStyle';
import { loginMember } from '../../api/members/loginMember';
import { useNavigate } from 'react-router-dom'
import { Header } from '../../components/Header/Header';

export const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // useNavigate 훅을 사용하여 초기화

  useEffect(() => {
    // 페이지가 로드될 때 로컬 스토리지에 accessToken이 있는지 확인
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      navigate('/'); // accessToken이 있다면 메인 페이지로 리디렉션
    }
  }, [navigate]);


  const handleSubmit = async (e) => {
    e.preventDefault();
    // 여기에 로그인 로직을 추가합니다. 예를 들어, API 호출 등
    const loginInfo = {
      email,
      password
    }

    try {
      const { nickname, accessToken } = await loginMember(loginInfo);
      localStorage.setItem('nickname', nickname);
      localStorage.setItem('accessToken', accessToken);
      navigate('/'); // 로그인 성공 후 메인 페이지로 리디렉션
    } catch (exception) {
      alert(exception.response.data.message);
    }
  };

  return (
    <>
      <Header />
      <LoginContainer>

        <LoginForm onSubmit={handleSubmit}>
          <h2>로그인</h2>
          <InputGroup>
            <Label htmlFor="email">이메일</Label>
            <Input
              type="text"
              id="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </InputGroup>
          <InputGroup>
            <Label htmlFor="password">비밀번호</Label>
            <Input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </InputGroup>
          <Button type="submit">로그인</Button>
        </LoginForm>
      </LoginContainer>
    </>
  );
}