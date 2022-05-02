package com.seung.practice.member.apiController;

import com.seung.practice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberRepository memberRepository;



}