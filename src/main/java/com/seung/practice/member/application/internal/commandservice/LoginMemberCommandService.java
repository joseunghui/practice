package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginMemberCommandService implements UserDetailsService {
	private final MemberRepository memberRepository;

	// 로그인
	public Optional<Member> loginMember(String memberId) {
		Optional<Member> member = memberRepository.findByMemberId(memberId);
		System.out.println("memberId = " + memberId);

		// 검증 -> 존재하지 않는 아이디
		if (!member.isPresent()) {
			throw new MemberException(ErrorCode.USER_NOT_FOUND);
		}
		return member;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByMemberId(username)
				.orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));
	}
}
