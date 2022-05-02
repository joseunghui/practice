package com.seung.practice.member.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@Builder
public class MemberFormDto {

	@NotBlank(message = "아이디는 필수 입니다.")
	private String memberId;

	@NotBlank(message = "비밀번호는 필수 입니다.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}",
			message = "비밀번호는 영문자와 숫자, 특수기호가 1개 이상 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;

	@NotBlank(message = "회원 이름은 필수 입니다.")
	private String name;

	@Pattern(regexp = "(01[016789])(-)(\\d{3,4})(-)(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phone;

	private String gender;
	private String birth; //TODO input format 정의

	@Email(message = "이메일 형식이 아닙니다.")
	private String email;

	private String city;
	private String street;
	private String zipcode;



}