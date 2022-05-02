package com.seung.practice.repository;


import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = true)
	void save() {
		Member member = new Member();
//
//		member.setMemberId("ex1");
//		member.setPassword("1111");
//		member.setName("Name1");

		memberRepository.save(member);

		assertThat(memberRepository.findOne(member.getId()).getId()).isEqualTo(3L);
	}

	@Test
	@Rollback(value = true)
	void findOne() {
		// InitDB에서 넣어준 데이터 사용 (1L)
		Member memberOne = memberRepository.findOne(1L);
		Assertions.assertThat(memberRepository.findOne(memberOne.getId())).isEqualTo(memberOne);
	}

	@Test
	@Rollback(value = true)
	void findByMemberId() {

	}


	@Test
	@Rollback(value = true)
	void findAll() {


	}




}