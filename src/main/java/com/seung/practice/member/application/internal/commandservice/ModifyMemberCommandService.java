package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifyMemberCommandService {

    private final MemberRepository memberRepository;

    // 회원 수정 전 -> memberId 로 member 객체 내 정보 가져오기
    public void getMember(String memberId) {
        // 검증 1 -> 존재하지 않는 아이디
        validateNonexistentMember(memberId);

        // 여기서 member는 변경 전 회원 정보
        Optional<Member> member = memberRepository.findByMemberId(memberId);

        // 검증 2 -> 회원 아이디는 변경 불가
        if (!member.get().getMemberId().equals(memberId)) {
            throw new MemberException(ErrorCode.ID_UNCHANGEABLE);
        } else {
            // 회원 아이디가 동일하다면 기존 회원 정보를 삭제
            memberRepository.delete(member.get());
        }
    }

    // 검증 -> 존재하지 않는 아이디
    private void validateNonexistentMember(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);

        if (!member.isPresent()) {
            throw new MemberException(ErrorCode.USER_NOT_FOUND);
        }
    }
	
    // 회원 수정 실행
    public Member modifyMember(ModifyMemberCommand command) {
        Member member = new Member(command);
        memberRepository.save(member);
        return member;
    }
}
