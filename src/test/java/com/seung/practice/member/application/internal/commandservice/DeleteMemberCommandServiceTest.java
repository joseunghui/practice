package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import com.seung.practice.testutil.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DeleteMemberCommandServiceTest {
	@InjectMocks
	private DeleteMemberCommandService deleteMemberCommandService;

	@InjectMocks
	private AddMemberCommandService addMemberCommandService;

	@Mock
	private MemberRepository memberRepository;

	@Test
	@DisplayName("정상적으로 기존 회원 정보를 삭제한다.")
	void deleteMember() {
		// 회원 가입 우선 실행
		AddMemberCommand cmd = TestData.mockAddMemberCommand;
		Member member = TestData.mockMember;

		Member actualMember = addMemberCommandService.addMember(cmd);
		given(memberRepository.findByMemberId(anyString())).willReturn(Optional.empty());

		// 회원 정보 일치하는지 확인
		Assertions.assertThat(actualMember)
				.isEqualTo(deleteMemberCommandService.getMember(actualMember.getMemberId()).get());
		
		// 회원 삭제 실행
		

	}
}
