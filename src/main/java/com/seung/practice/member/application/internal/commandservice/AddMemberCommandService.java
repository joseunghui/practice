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
		// 중복회원 검증
        validateDuplicateMember(command);

        Member member = new Member(command);
        memberRepository.save(member);
		
        return member;
    }
	
	// 중복 회원 검증
    private void validateDuplicateMember(AddMemberCommand command) {
        memberRepository.findByMemberId(command.getMemberId()).ifPresent(m ->{
			throw new MemberException(ErrorCode.USER_EXISTS);
		});
    }
}