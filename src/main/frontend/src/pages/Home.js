import React from 'react';
import Button from "../lib/styles/Button";

const Home = () => {
    console.log("여기는 Home, 기본화면!");
    return (
        <>
            <div>
                <Button>로그인</Button>
            </div>

            <div>
                <Button>회원가입</Button>
            </div>

            <div>
                <Button>회원목록</Button>
            </div>
        </>
    );
};

export default Home;