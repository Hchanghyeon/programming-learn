import styled from 'styled-components';

export const FloatingButton = styled.button`
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background-color: #007bff;
  color: white;
  border: none;
  font-size: 24px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

export const MenuContainer = styled.div`
  position: fixed;
  bottom: 90px;
  right: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

export const MenuItem = styled.button`
  background-color: #007bff;
  border: none;
  color: white;
  padding: 10px;
  margin: 5px 0;
  border-radius: 5px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

export const Button = styled.button`
    cursor: pointer;
    background-color: #007bff;
    border-style: none;
    color:white;
`