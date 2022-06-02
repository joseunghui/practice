import React from 'react';
import Button from "../components/common/Button";

const Home = () => {
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