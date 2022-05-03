package com.seung.practice.member.domain.model.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ModifyMemberCommand {
	// 회원 아이디는 변경 불가
	private String password;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private LocalDateTime birth;
	private String city;
	private String street;
	private String zipcode;
}
