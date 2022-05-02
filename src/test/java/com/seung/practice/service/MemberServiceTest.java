package com.seung.practice.service;

import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = true)
	void join() {
		Member member = new Member();

	}


}