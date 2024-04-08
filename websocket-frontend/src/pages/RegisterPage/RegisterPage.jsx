import React, { useState, useEffect } from 'react';
import { Container, Form, InputGroup, Input, Button, ErrorMessage } from './RegisterPageStyle';
import { registerMemger } from '../../api/members/registerMember';
import { useNavigate } from 'react-router-dom'
import { Header } from '../../components/Header/Header';

function RegisterPage() {
  const [form, setForm] = useState({
    nickname: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    // 페이지가 로드될 때 로컬 스토리지에 accessToken이 있는지 확인
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      navigate('/'); // accessToken이 있다면 메인 페이지로 리디렉션
    }
  }, [navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: value,
    });
    setErrorMessage('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (form.password !== form.confirmPassword) {
      setErrorMessage('패스워드가 일치하지 않습니다.');
      return;
    }

    try {
      await registerMemger(form);
      navigate("/login");
    } catch (exception) {
      alert(exception.response.data.message);
    }
  };

  return (
    <>    
      <Header/>
      <Container>
        <Form onSubmit={handleSubmit}>
          <h2>회원가입</h2>
          <InputGroup>
            <label htmlFor="nickname">닉네임</label>
            <Input
              id="nickname"
              name="nickname"
              value={form.nickname}
              onChange={handleChange}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor="username">회원명</label>
            <Input
              id="username"
              name="username"
              value={form.username}
              onChange={handleChange}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor="email">이메일</label>
            <Input
              type="email"
              id="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor="password">패스워드</label>
            <Input
              type="password"
              id="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor="confirmPassword">패스워드 확인</label>
            <Input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={form.confirmPassword}
              onChange={handleChange}
              required
            />
          </InputGroup>
          {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
          <Button type="submit">회원가입</Button>
        </Form>
      </Container>
    </>
  );
}

export default RegisterPage;