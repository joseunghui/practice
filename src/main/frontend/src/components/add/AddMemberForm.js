import React from "react";
// import { Link } from "react-router-dom";
import FormBlock from "../../lib/styles/FormBlock";
import StyledInput from "../../lib/styles/InputStyle";
import RightBtn from "../../lib/styles/RightBtn";

const AddMemberForm = () => {

    const onClickBtn = (e) => {
        e.preventDefault();

    }

    return (
        <FormBlock>
            <form>
                <StyledInput autoComplete="username" name="memberId" placeholder="아이디" type="text" />
                <StyledInput autoComplete="current-password" name="password" placeholder="비밀번호" type="password" />
                <StyledInput name="name" placeholder="이름" type="text" />
                <StyledInput name="email" placeholder="이메일" type="email" />
                <StyledInput name="gender" placeholder="성별" type="text" />
                <StyledInput name="phone" placeholder="전화번호" type="text" />

                <StyledInput name="city" placeholder="주소1" type="text" />
                <StyledInput name="street" placeholder="주소2" type="text" />
                <StyledInput name="zipcode" placeholder="주소3" type="text" />

                <RightBtn onClick={onClickBtn}>회원가입!!</RightBtn>
            </form>
        </FormBlock>
    );
};

export default AddMemberForm;