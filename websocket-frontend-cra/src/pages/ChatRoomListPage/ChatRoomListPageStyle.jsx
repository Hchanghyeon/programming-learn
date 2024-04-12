import styled from 'styled-components';

// 스타일드 컴포넌트 정의
export const ListContainer = styled.div`
width:400px;
  display: flex;
  flex-direction: column;
  margin: 20px;
`;

export const RoomContainer = styled.div`
  border: 1px solid #ccc;
  border-radius: 8px;
  margin: 10px 0;
  padding: 20px;
`;

export const Title = styled.div`
  font-size: 20px;
`;
export const TitleContainer = styled.div`
    display:flex;
    justify-content: space-between;
    align-items: center;
`

export const ButtonContainer = styled.div`
    display:flex;
    justify-content: end;
    align-items: center;
`
export const ContentContainer = styled.div`
    display: flex;
    justify-content: space-between;
`

export const Details = styled.div`
  margin: 10px 0;
  color:black;
  font-size:13px;
`;

export const Button = styled.button`
  background-color: blue;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
`;