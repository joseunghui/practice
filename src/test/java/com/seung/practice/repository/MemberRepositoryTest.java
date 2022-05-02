package com.seung.practice.repository;


import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = true)
	void save() {
		Member member = new Member();

		memberRepository.save(member);

	}

	@Test
	@Rollback(value = true)
	void findOne() {

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