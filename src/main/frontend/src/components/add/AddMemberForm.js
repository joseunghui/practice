import React, { useState } from "react";
// import { Link } from "react-router-dom";
import FormBlock from "../../lib/styles/FormBlock";
import StyledInput from "../../lib/styles/InputStyle";
import RightBtn from "../../lib/styles/RightBtn";
import axios from "axios";

const AddMemberForm = () => {

    const [values, setValues] = useState({
        // 변수 값 설정 -> values 로 묶기
        memberId: "",
        password: "",
        name: "",
        gender: "",
        email: "",
        phone: "",
        birth: "",
        city: "",
        street: "",
        zipcode: "",
    }); // TODO : 따로 빼야함

    const onchangeInput = (e) => {
        // 값 입력 시 values 값 변경
        const {name, value} = e.target;
        setValues({...values, [name]: value});
    };

    const onClickBtn = (e) => {
        e.preventDefault();

        axios({
            method: 'post',
            url: '/api/members/add',
            withCredentials: true,
            params: {
                memberId : values.memberId,
                password: values.password,
                name: values.name,
                gender: values.gender,
                email: values.email,
                phone: values.phone,
                birth: values.birth,
                city: values.city,
                street: values.street,
                zipcode: values.zipcode,
            }
        }).then(() => {
            document.location.href = "/";  // TODO -> 추후 변경
        }).catch((Error) => { console.log(Error)});
    }

    return (
        <FormBlock>
            <form>
                <StyledInput name="memberId" placeholder="아이디" onChange={onchangeInput} />
                <StyledInput name="password" placeholder="비밀번호" type="password" onChange={onchangeInput} />
                <StyledInput name="name" placeholder="이름" onChange={onchangeInput} />

                <StyledInput name="email" placeholder="이메일" type="email" onChange={onchangeInput} />
                <StyledInput name="gender" placeholder="성별" onChange={onchangeInput} />
                <StyledInput name="phone" placeholder="전화번호" onChange={onchangeInput} />
                <StyledInput name="birth" placeholder="생년월일" type="date" onChange={onchangeInput} />


                <StyledInput name="city" placeholder="주소1" onChange={onchangeInput} />
                <StyledInput name="street" placeholder="주소2" onChange={onchangeInput} />
                <StyledInput name="zipcode" placeholder="주소3" onChange={onchangeInput} />

                <RightBtn onClick={onClickBtn}>회원가입</RightBtn>
            </form>
        </FormBlock>
    );
};

export default AddMemberForm;