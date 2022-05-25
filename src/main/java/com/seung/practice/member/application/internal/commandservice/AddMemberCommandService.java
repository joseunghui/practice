package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddMemberCommandService {
    private final MemberRepository memberRepository;

	// 회원 가입
    public Member addMember(AddMemberCommand command){
        validateDuplicateMember(command.getMemberId());

        Member member = new Member(command);
        memberRepository.save(member);
        return member;
    }


    private void validateDuplicateMember(String memberId) {
        Optional<Member> findMembers = memberRepository.findByMemberId(memberId);

        if (!findMembers.isEmpty()) {
			// MemberException 에서 이미 존재하는 회원 에러 : USER_EXISTS
			throw new MemberException(ErrorCode.USER_EXISTS);
        }
    }
}