import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const HeaderContainer = styled.div`
position: fixed;
  top:0px;
  width:100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  box-sizing: border-box;
  background-color: #f0f0f0;
`;

export const Logo = styled.div`
  font-size: 24px;
  font-weight: bold;
`;

export const Navigation = styled.nav`
  display: flex;
  gap: 20px;
  font-size: 16px;
`;

export const StyledLink = styled(Link)`
  text-decoration: none;
  color: #333;

  &:hover {
    text-decoration: underline;
  }
`;

export const StyledA = styled.a`
  text-decoration: none;
  color: #333;

  &:hover {
    text-decoration: underline;
  }
`;


export const NicknameContent = styled.div`
    
`