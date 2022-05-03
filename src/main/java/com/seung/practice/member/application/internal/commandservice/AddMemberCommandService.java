package com.seung.practice.member.application.internal.commandservice;

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
            throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
        }
    }
}