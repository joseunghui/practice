import React from 'react';
import styled from 'styled-components';
import palette from './Palette';

// button styling
const StyledButton = styled.button`
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  
  margin: 10px; 
  padding: 0.25rem 1rem;
  
  color: white;
  outline: none;
  cursor: pointer;

  background: ${palette.gray[5]};
  &:hover {
    background: ${palette.teal[8]};
  }
`;
// Button 에 받아오는 props 를 모두 styledButton 에 전달한다는 의미
const Button = (props) => {
    return <StyledButton {...props} />;
};

export default Button;