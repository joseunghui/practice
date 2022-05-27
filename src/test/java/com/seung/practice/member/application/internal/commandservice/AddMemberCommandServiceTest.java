package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import com.seung.practice.testutil.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AddMemberCommandServiceTest {
    @InjectMocks
    private AddMemberCommandService addMemberCommandService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("정상적으로 회원을 생성한다.")
    void addMember(){
        //given
        AddMemberCommand cmd = TestData.mockAddMemberCommand;
        Member member = TestData.mockMember;

        // given(memberRepository.findByMemberId(anyString())).willReturn(Lists.newArrayList()); <- 이게 원문....
        given(memberRepository.findByMemberId(anyString())).willReturn(Optional.empty());

        //when
        Member actualMember = addMemberCommandService.addMember(cmd);
        //then
        assertAll(
                () -> assertThat(actualMember.getMemberId()).isEqualTo(member.getMemberId()),
				() -> assertThat(actualMember.getPassword()).isEqualTo(member.getPassword()),
				() -> assertThat(actualMember.getName()).isEqualTo(member.getName()),
				() -> assertThat(actualMember.getEmail()).isEqualTo(member.getEmail()),
				() -> assertThat(actualMember.getBirth()).isEqualTo(member.getBirth()),
				() -> assertThat(actualMember.getGender()).isEqualTo(member.getGender()),
				() -> assertThat(actualMember.getPhone()).isEqualTo(member.getPhone()),
				() -> assertThat(actualMember.getAddress().getStreet()).isEqualTo(member.getAddress().getStreet()),
				() -> assertThat(actualMember.getAddress().getCity()).isEqualTo(member.getAddress().getCity()),
				() -> assertThat(actualMember.getAddress().getZipcode()).isEqualTo(member.getAddress().getZipcode())
        );
    }

    @Test
    @DisplayName("중복 회원 ID로 생성에 실패 한다.")
    void addMemberDuplicatedError(){
		//given
		AddMemberCommand cmd1 = TestData.mockAddMemberCommand;
		AddMemberCommand cmd2 = TestData.mockAddMemberCommand;
		given(memberRepository.findByMemberId(anyString())).willReturn(Optional.empty());

		addMemberCommandService.addMember(cmd1);

		assertThrows(MemberException.class, () -> addMemberCommandService.addMember(cmd2));
    }
}