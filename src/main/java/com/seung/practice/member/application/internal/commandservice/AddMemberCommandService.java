package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddMemberCommandService {
    private final MemberRepository memberRepository;

    public Member addMember(AddMemberCommand command){
        validateDuplicateMember(command.getMemberId());

        Member member = new Member(command);
        memberRepository.save(member); //JPA save 사용시 object return 받을 수 있음.
        return member;
    }


    private void validateDuplicateMember(String memberId) {
        //TODO: List로 찾을 이유 없음. Optional<Member>로 조회 하도록 수정해주세요

        List<Member> findMembers = memberRepository.findByMemberId(memberId);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
        }
    }
}