package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginMemberCommandService {
	private final MemberRepository memberRepository;

	// 로그인
	public Optional<Member> loginMember(String memberId) {
		// 검증 -> 존재하지 않는 아이디
		validateNonexistentMember(memberId);

		Optional<Member> member = memberRepository.findByMemberId(memberId);

		return member;
	}

	// 검증 -> 존재하지 않는 아이디
	private void validateNonexistentMember(String memberId) {
		Optional<Member> member = memberRepository.findByMemberId(memberId);

		if (!member.isEmpty()) {
			throw new IllegalStateException("존재하지 않는 회원 아이디 입니다.");
		}
	}

}
