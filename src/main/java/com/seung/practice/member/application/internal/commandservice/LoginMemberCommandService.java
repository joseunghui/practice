package com.seung.practice.member.application.internal.commandservice;

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

		// 검증 -> 존재하지 않는 아이디
		if (!member.isPresent()) {
			throw new IllegalStateException("존재하지 않는 회원 아이디 입니다.");
		}
		return member;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByMemberId(username)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
	}
}
