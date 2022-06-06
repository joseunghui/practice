import React from 'react';
import { Link } from 'react-router-dom';
import AuthTemplateBlock from '../../lib/styles/TemplateBlock';
import WhiteBox from '../../lib/styles/Box';

const LoginTemplate = ({children}) => {
    // 부모 컴포넌트 안에 있는 자식 컴포넌트 요소 띄우기
    return (
        <AuthTemplateBlock>
            <WhiteBox>
                <div className="logo-area">
                    <Link to='/login'>로그인</Link>
                </div>
                {children}
            </WhiteBox>
        </AuthTemplateBlock>
    );
};

export default LoginTemplate;