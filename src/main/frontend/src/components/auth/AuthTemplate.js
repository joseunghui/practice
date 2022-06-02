import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import palette from '../../lib/styles/Palette';

const AuthTemplateBlock = styled.div`
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  right:0;
  background : ${palette.orange[2]};
  display: flex;
  flex-direction:column;
  justify-content: center;
  align-items: center;
  `;

const WhiteBox = styled.div`
  .logo-area {
    display: block;
    padding-bottom: 2rem;
    text-align:center;
    font-weight: bold;
    letter-spacing:2px;
  }
  box-shadow: 0 0 8px rgba(0,0,0,0.025);
  padding: 2rem;
  width: 360px;
  background: white;
  border-radius: 2px;
  `;

const AuthTemplate = ({children}) => {
    // 부모 컴포넌트 안에 있는 자식 컴포넌트 요소 띄우기
    return (
        <AuthTemplateBlock>
            <WhiteBox>
                {/* 전부 스타일 컴포넌트로 만들어주지 않고 가독성을 위해 css selector 사용 */}
                <div className="logo-area">
                    <Link to='/'>TITLE</Link>
                </div>
                {children}
            </WhiteBox>
        </AuthTemplateBlock>
    );
};

export default AuthTemplate;