import React, {useState} from "react";
import AuthFormBlock from "../../lib/styles/FormBlock";
import StyledInput from "../../lib/styles/InputStyle";
import RightBtn from "../../lib/styles/RightBtn";
import axios from "axios";
import {Link} from "react-router-dom";
import Route from "react-router-dom/es/Route";

const LoginForm = () => {

    const [values, setValues] = useState({
        // 변수 값 설정 -> values 로 묶기
        memberId: "",
        password: "",
    });

    const onchangeInput = (e) => {
        // 값 입력 시 values 값 변경
        const {name, value} = e.target;
        setValues({...values, [name]: value});
    };

    const onclickBtn = (e) => {
        // submit
        e.preventDefault();

        axios({
            method: 'post',
            url: '/api/members/login',
            withCredentials: true,
            params: {
                memberId : values.memberId,
                password: values.password,
            }
        }).then((res) => {
            document.location.href = '/';
        }).catch((Error) => { console.log(Error)});
    }

    return (
        <AuthFormBlock>
            <form>
                {/* autocomplete 속성 >> 인풋에 자동완성하는 속성
                 username 은 사용자 이름 , new-password 는 보통 비밀번호 자동완성 막기 위해서 새로운 비밀번호나 비밀번호 확인란에 들어간다는뎅 ㅎ..
                 머 일단 책은 이렇고 나는 나중에 보고 current-password 가 더 맞을거 같아서 임의로 바꿨음. */}
                <StyledInput
                    autoComplete="username"
                    name="memberId"
                    placeholder="아이디"
                    onChange={onchangeInput}
                />
                <StyledInput
                    autoComplete="current-password"
                    name="password"
                    placeholder="비밀번호"
                    type="password"
                    onChange={onchangeInput}
                />

                <RightBtn onClick={onclickBtn}>로그인</RightBtn>
            </form>

        </AuthFormBlock>
    );
};
export default LoginForm;