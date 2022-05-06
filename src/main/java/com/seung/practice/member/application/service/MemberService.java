package com.seung.practice.member.application.service;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 전체 목록
    public List<Member> memberFindAll() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }




}
