package com.seung.practice.member.service;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	// 의존 관계 주입
	private final MemberRepository memberRepository;
//	private final PasswordEncoder pwEnc;

	// 회원 가입
//	@Transactional
//	public Long join(Member member) {
//		// 중복 회원 검사
//		validateDuplicateMember(member);
//
//		// 비번 암호화
//		member.setPassword(pwEnc.encode(member.getPassword()));
//
//		// 회원 정보 저장
//		memberRepository.save(member);
//
//		return member.getId();
//	}

	// 중복 회원 예외 처리
	private void validateDuplicateMember(Member member) {
		//중복으로 들어 갈 수 없는 값을 list로 찾을 이유가 없음.
		List<Member> findMembers = memberRepository.findByMemberId(member.getMemberId());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
		}
	}



}