package com.seung.practice.testutil;

import com.seung.practice.member.controller.dto.AddMemberFormDto;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;

public class TestData {
    public static AddMemberFormDto mockAddMemberFormDto = AddMemberFormDto.builder()
            .memberId("test123")
            .password("!23Qwe")
            .name("테스터")
            .phone("010-1234-5678")
			.birth("2000-01-01")
			.email("aaa@gmail.com")
			.gender("male")
            .city("서울")
            .street("성수1가")
            .zipcode("12345")
            .build();

    public static AddMemberCommand mockAddMemberCommand = AddMemberCommand.builder()
            .memberId(mockAddMemberFormDto.getMemberId())
            .password(mockAddMemberFormDto.getPassword())
            .name(mockAddMemberFormDto.getName())
            .phone(mockAddMemberFormDto.getPhone())
            .city(mockAddMemberFormDto.getCity())
            .street(mockAddMemberFormDto.getStreet())
            .zipcode(mockAddMemberFormDto.getZipcode())
            .build();

    public static Member mockMember = new Member(mockAddMemberCommand);

    public static String MockMemberFormString = "memberId=test123&password=!23Qwe&name=테스터&phone=010-1234-5678&birth=2000-01-01&email=aaa@gmail.com&gender=male&city=서울&street=성수1가&zipcode=12345";






}