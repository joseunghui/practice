package com.seung.practice.service;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import com.seung.practice.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = true)
	void join() {
		Member member = new Member();

//		member.setMemberId("ex1");
//		member.setPassword("1111");
//		member.setName("Name1");

//		Long savedId = memberService.join(member);

//		assertEquals(member, memberRepository.findOne(savedId));
	}


}