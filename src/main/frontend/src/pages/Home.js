import React from 'react';
import Button from "../lib/styles/Button";

const Home = () => {

    const onClickLoginBtn = (e) => {
        document.location.href = "/api/members/login";
    }

    const onClickAddBtn = (e) => {
        document.location.href = "api/members/add";
    }

    const onClickMemberListBtn = (e) => {
        document.location.href = "/api/members";
    }

    return (
        <>
            <div>
                <Button onClick={onClickLoginBtn}>로그인</Button>
            </div>

            <div>
                <Button onClick={onClickAddBtn}>회원가입</Button>
            </div>

            <div>
                <Button onClick={onClickMemberListBtn}>회원목록</Button>
            </div>
        </>
    );
};

export default Home;