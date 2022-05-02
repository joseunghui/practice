package com.seung.practice.member.application.internal.commandservice;

import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyMemberCommandService {

    private final MemberRepository memberRepository;
}
