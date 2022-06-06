import styled from "styled-components";
import palette from "./Palette";

const StyledInput = styled.input`
    font-size:1rem;
    border:none;
    border-bottom: 1px solid ${palette.teal[8]};
    padding-bottom: 0.5rem;
    outline: none;
    width: 100%;
    &:focus{
        color: $oc-teal-7;
        border-bottom:1px solid ${palette.orange[3]} ;
    }
    /* Scss 에서 쓰는 요소가 서로 반복될 때 margin-top 을 줌 >>> input 과 input 사이에 margin-top 줌. */
    &+&{
        margin-top: 1rem;
    }
    `;

export default StyledInput;