import React, {useState} from "react";
import AuthFormBlock from "../../lib/styles/FormBlock";
import StyledInput from "../../lib/styles/InputStyle";
import RightBtn from "../../lib/styles/RightBtn";
import axios from "axios";

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