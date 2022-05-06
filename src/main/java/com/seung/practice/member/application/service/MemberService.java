package com.seung.practice.member.application.service;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 전체 목록
    public List<Member> memberFindAll() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }


    // 회원 아이디로 조회
    public Member memberFindOne(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        return member.get();
    }
}
