import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/Palette';

// button styling
const StyledButton = styled.button`
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  padding: 0.25rem 1rem;
  color: white;
  outline: none;
  cursor: pointer;
  
  background: ${palette.orange[4]};
  &:hover {
    background: ${palette.orange[3]};
  }
`;
// Button 에 받아오는 props 를 모두 styledButton 에 전달한다는 의미
const Button = (props) => {
    return <StyledButton {...props} />;
};

export default Button;