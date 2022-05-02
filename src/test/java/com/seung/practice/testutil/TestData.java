package com.seung.practice.testutil;

import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;

public class TestData {
    public static MemberFormDto mockMemberFormDto = MemberFormDto.builder()
            .memberId("test123")
            .password("!23Qwe")
            .name("테스터")
            .phone("010-1234-5678")
            .city("서울")
            .street("성수1가")
            .zipcode("12345")
            .build();

    public static AddMemberCommand mockAddMemberCommand = AddMemberCommand.builder()
            .memberId(mockMemberFormDto.getMemberId())
            .password(mockMemberFormDto.getPassword())
            .name(mockMemberFormDto.getName())
            .phone(mockMemberFormDto.getPhone())
            .city(mockMemberFormDto.getCity())
            .street(mockMemberFormDto.getStreet())
            .zipcode(mockMemberFormDto.getZipcode())
            .build();

    public static Member mockMember = new Member(mockAddMemberCommand);

    public static String MockMemberFormString = "memberId=test123&password=!23Qwe&name=테스터&phone=010-1234-5678&city=서울&street=성수1가&zipcode=12345";
}