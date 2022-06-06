import React from 'react';
import { Link } from 'react-router-dom';
import TemplateBlock from "../../lib/styles/TemplateBlock";
import WhiteBox from "../../lib/styles/Box";

const AddTemplate = ({children}) => {
    // 부모 컴포넌트 안에 있는 자식 컴포넌트 요소 띄우기
    return (
        <TemplateBlock>
            <WhiteBox>
                {/* 전부 스타일 컴포넌트로 만들어주지 않고 가독성을 위해 css selector 사용 */}
                <div className="logo-area">
                    <Link to='/add'>회원가입</Link>
                </div>
                {children}
            </WhiteBox>
        </TemplateBlock>
    );
};

export default AddTemplate;