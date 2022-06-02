package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteMemberCommandService {

    private final MemberRepository memberRepository;

    // 회원 탈퇴 전 기존 회원 확인
    public Optional<Member> getMember(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
		
		// 기존 회원 인지 확인
		validateUserNotFound(member);

        return member;
    }
	// 검증 -> 존재하지 않는 아이디
	private void validateUserNotFound(Optional<Member> member) {
		memberRepository.findByMemberId(member.get().getMemberId()).ifPresent(m -> {
			throw new MemberException(ErrorCode.USER_NOT_FOUND);
		});
	}

	// 삭제 실행
    public void deleteMember(Optional<Member> member) {
        memberRepository.delete(member.get());
    }
}
